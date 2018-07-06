/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.common;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 查询条件编辑
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class SearchUtils {

    private static final String[] PARSE_PATTERNS = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchUtils.class);
    
    /**
     * 查询开始日期
     * @param date 日期("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss")
     * @return 日期(0时0分)
     * @since  1.0
     */
    public static Date dayFrom(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            Date d = DateUtils.parseDate(date, PARSE_PATTERNS);
            return DateUtils.truncate(d, Calendar.DATE);
        } catch (ParseException e) {
            LOGGER.warn("日期转换错误{},{}", date, e.getMessage());
        }
        return null;
    }

    /**
     * 查询终止日期
     * @param date 日期("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss")
     * @return 日期(次日0时0分)
     * @since  1.0
     */
    public static Date dayTo(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            Date d = DateUtils.parseDate(date, PARSE_PATTERNS);
            return DateUtils.ceiling(d, Calendar.DATE);
        } catch (ParseException e) {
            LOGGER.warn("日期转换错误{},{}", date, e.getMessage());
        }
        return null;
    }
}
