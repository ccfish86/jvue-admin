/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import net.ccfish.jvue.service.AclResourceService;
import net.ccfish.jvue.vm.AclResource;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Service
public class AclResourceServiceImpl implements AclResourceService {

    private final Logger logger = LoggerFactory.getLogger(AclResourceServiceImpl.class);

    @Autowired
    private HazelcastInstance hazelcastInstance;
    
    /* (non-Javadoc)
     * @see net.ccfish.jvue.service.AclResourceService#getName(java.lang.String)
     */
    @Override
    public String getName(Integer id) {
        MultiMap<Integer, AclResource> resourcesMap =
                hazelcastInstance.getMultiMap("acl-resource");
        
        Integer classId = id - id % 100;
        Collection<AclResource> apiResources = resourcesMap.get(classId);
        for (AclResource ar: apiResources) {
            if (Objects.equals(ar.getId(), id)) {
                return ar.getName();
            }
        }
        
        logger.warn("Cant find the api-name for id {}", id);
        
        return "";
    }

    /* (non-Javadoc)
     * @see net.ccfish.jvue.service.AclResourceService#getAll()
     */
    @Override
    public List<AclResource> getAll() {
      MultiMap<Integer, AclResource> resourcesMap =
              hazelcastInstance.getMultiMap("acl-resource");
      return Lists.newArrayList(resourcesMap.values());
    }

}
