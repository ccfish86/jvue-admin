package net.ccfish.jvue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import net.ccfish.common.enums.YesOrNoEnum;
import net.ccfish.common.mybatis.BaseMapper;
import net.ccfish.jvue.autogen.dao.JvueModuleMapper;
import net.ccfish.jvue.autogen.dao.JvuePageMapper;
import net.ccfish.jvue.autogen.model.JvueModule;
import net.ccfish.jvue.autogen.model.JvuePage;
import net.ccfish.jvue.service.JvueModuleService;

/**
 * Generated by Spring Data Generator on 31/01/2018
 */
@Service
@Transactional
public class JvueModuleServiceImpl implements JvueModuleService {

    private JvueModuleMapper jvueModuleMapper;

    @Autowired
    private JvuePageMapper jvuePageMapper;

    @Autowired
    public JvueModuleServiceImpl(JvueModuleMapper jvueModuleMapper) {
        this.jvueModuleMapper = jvueModuleMapper;
    }

    @Override
    public BaseMapper<JvueModule> baseMapper() {
        return this.jvueModuleMapper;
    }

    @Override
    @CacheEvict(value = "JwtUserDetailsService", allEntries = true)
    public void delete(Integer id) {

        Assert.notNull(id, "模块ID不能为空");
        // 判断是否在其他表中使用，未使用时，可物理删除；如在page等表里使用，逻辑删除
        JvuePage jvuePage = new JvuePage();
        jvuePage.setModuleId(id);
        long mcount = jvuePageMapper.selectCount(jvuePage);
        if (mcount > 0L) {
            // BaseMapper().delete(id);
            JvueModule entityResult = jvueModuleMapper.selectByPrimaryKey(id);
            if (entityResult != null) {
                entityResult.setEnabled(YesOrNoEnum.No.ordinal());
                jvueModuleMapper.updateByPrimaryKeySelective(entityResult);
            }
        } else {
            jvueModuleMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    @CacheEvict(value = "JwtUserDetailsService", allEntries = true)
    public JvueModule update(Integer id, JvueModule data) {
        // throw new UnsupportedClassVersionError("不支持更新处理");
        JvueModule jmodule = null;
        JvueModule module = jvueModuleMapper.selectByPrimaryKey(id);
        if (module != null) {
            module.setName(data.getName());
            module.setEnabled(data.getEnabled());
            jvueModuleMapper.updateByPrimaryKeySelective(module);
            return module;
        }
        return jmodule;
    }

}
