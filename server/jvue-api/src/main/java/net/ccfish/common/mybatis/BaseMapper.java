/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.common.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.provider.SpecialProvider;

/**
 * Mapper for PostgreSql
 * 
 * @author yuan
 * @version 1.0
 * @since 1.0
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T>, Marker {


    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}
