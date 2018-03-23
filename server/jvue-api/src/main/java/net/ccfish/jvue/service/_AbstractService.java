/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
        Optional<T> result = jpaRepository().findById(id);
        return result.get();
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
        jpaRepository().deleteById(id);
    }

    default T save(T obj) {
        T data = jpaRepository().save(obj);
        return data;
    }

    default List<T> save(List<T> objs) {
        List<T> datas = jpaRepository().saveAll(objs);
        return datas;
    }

    default T update(ID id, T data) {
        throw new UnsupportedClassVersionError("不支持更新处理");
    }
}
