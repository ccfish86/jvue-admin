/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageHelper;

import net.ccfish.common.OrderByUtils;
import net.ccfish.common.mybatis.BaseMapper;
import net.ccfish.common.web.PageParam;

/**
 * 基本Service类
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface _AbstractService<T, ID extends Serializable> {

    BaseMapper<T> baseMapper();

    default T getOne(ID id) {
        T result = baseMapper().selectByPrimaryKey(id);
        return result;
    }

    default List<T> getAll() {
        return baseMapper().selectAll();
    }

    default List<T> getAll(PageParam pageParam) {
        String orderBy = OrderByUtils.toString(pageParam.getSort(), pageParam.getDirection());
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize(), orderBy);
        return baseMapper().selectAll();
    }
    
    default void delete(ID id) {
        baseMapper().deleteByPrimaryKey(id);
    }

    default T save(T obj) {
        baseMapper().insert(obj);
        return obj;
    }

    default List<T> save(List<T> objs) {
        baseMapper().insertList(objs);
        return objs;
    }

    default T update(ID id, T data) {
        throw new UnsupportedClassVersionError("不支持更新处理");
    }
}
