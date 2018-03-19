/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest.pub;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ccfish.common.enums.YesOrNoEnums;
import net.ccfish.common.web.BaseModel;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("pub")
public class PubDictController {

    @GetMapping("dict/enums")
    public BaseModel<Map<String, Map<String, String>>> enums() {
        Map<String, Map<String, String>> enums = new LinkedHashMap<String, Map<String,String>>();
        
        Map<String, String> yns = new LinkedHashMap<>();
        for (YesOrNoEnums yn: YesOrNoEnums.values()) {
            yns.put(String.valueOf(yn.ordinal()), yn.name());
        }
        enums.put("yn", yns);
        
        return new BaseModel<Map<String, Map<String, String>>>().setData(enums);
    }
}
