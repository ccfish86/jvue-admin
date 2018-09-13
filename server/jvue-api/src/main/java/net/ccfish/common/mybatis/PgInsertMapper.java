/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * 通用Mapper接口,基础查询
 *
 * @param <T> 不能为空
 * @author yuan
 */
interface PgInsertMapper<T> extends BaseInsertMapper<T> {

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insert(T record);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insertSelective(T record);
}
