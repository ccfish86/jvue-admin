/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.config;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

/**
 * 分布式缓存配置，分布式锁及消息
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableCaching
@AutoConfigureAfter(value = { DataSourceAutoConfiguration.class })
public class HazelCastConfigration {

    private final Logger log = LoggerFactory.getLogger(HazelCastConfigration.class);

    @Autowired(required = false)
    private Registration registration;

    @Autowired(required = false)
    private DiscoveryClient discoveryClient;

    @PreDestroy
    public void destroy() {
        log.info("Closing Cache Manager");
        Hazelcast.shutdownAll();
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        log.debug("Configuring Hazelcast");
        HazelcastInstance hazelCastInstance = Hazelcast.getHazelcastInstanceByName("jvue-server");
        if (hazelCastInstance != null) {
            log.debug("Hazelcast already initialized");
            return hazelCastInstance;
        }
        Config config = new Config();
        config.setInstanceName("jvue-server");
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        if (this.registration == null) {
            log.warn("No discovery service is set up, Hazelcast cannot create a cluster.");
        } else {
            String serviceId = registration.getServiceId();
            log.debug("Configuring Hazelcast clustering for instanceId: {}", serviceId);
            config.getNetworkConfig().setPort(5701);
            config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
            for (ServiceInstance instance : discoveryClient.getInstances(serviceId)) {
                String clusterMember = instance.getHost() + ":5701";
                log.debug("Adding Hazelcast (prod) cluster member " + clusterMember);
                config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(clusterMember);
            }
        }
        config.getMapConfigs().put("default", initializeDefaultMapConfig());
        config.getMapConfigs().put("net.ccfish.jvue.domain.*", initializeDefaultMapConfig());
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        log.debug("Starting HazelcastCacheManager");
        CacheManager cacheManager = new HazelcastCacheManager(hazelcastInstance);
        //cacheManager.setCacheOptions(options);
        return cacheManager;
    }
    
    private MapConfig initializeDefaultMapConfig() {
        MapConfig mapConfig = new MapConfig();
        mapConfig.setBackupCount(1);
        mapConfig.setEvictionPolicy(EvictionPolicy.LRU);
        mapConfig.setMaxSizeConfig(new MaxSizeConfig(0, MaxSizeConfig.MaxSizePolicy.USED_HEAP_SIZE));
        return mapConfig;
    }
}
