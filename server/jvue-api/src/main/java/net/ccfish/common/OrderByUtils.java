package net.ccfish.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class OrderByUtils {
    
    static String ASC = "asc";
    static String DESC = "desc";

    public static String toString(String sort, int direction) {
        
        if (StringUtils.isBlank(sort)) {
            return StringUtils.EMPTY;
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append(sort).append(StringUtils.SPACE).append(direction == 1 ? DESC : ASC);
        
        return builder.toString();
    }
}
