/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 基本Service类
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface _AbstractService<T, ID extends Serializable> {

    JpaRepository<T, ID> jpaRepository();

    default T getOne(ID id) {
        return jpaRepository().getOne(id);
    }

    default List<T> getAll() {
        return jpaRepository().findAll();
    }

    default Page<T> getAll(Pageable page) {
        return jpaRepository().findAll(page);
    }

    default Page<T> getAll(Example<T> example, Pageable page) {
        return jpaRepository().findAll(example, page);
    }

    default void delete(ID id) {
        jpaRepository().delete(id);
    }

    default void save(T obj) {
        jpaRepository().save(obj);
    }

    default void save(List<T> objs) {
        jpaRepository().save(objs);
    }

    default void update(ID id, T data) {
        throw new UnsupportedClassVersionError("不支持更新处理");
    }
}
