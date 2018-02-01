/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.common.web;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 各消息/服务间用通用Model
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class PagedModel<T> extends BaseModel<List<T>> {    //当前页

    private static final long serialVersionUID = -3398360572386723106L;
    
    private int pageNum;
    //每页的数量
    private int pageSize;
//    //当前页的数量
//    private int size;
    //总记录数
    private long total;
//    //总页数
//    private int pages;
    
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
//        return size;
        return super.getData() == null?0:super.getData().size();
    }

//    public void setSize(int size) {
//        this.size = size;
//    }
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
//        return pages;
        if (pageSize == 0 || total == 0) {
            return 0;
        }
        long pageMod = total % pageSize;
        long pageCount = total / pageSize;
        return pageMod == 0 ? pageCount : pageCount + 1;
    }
    
    /**
     * @param list
     * @return
     * @since  1.0
     */
    public static <T> PagedModel<T> from(Page<T> list) {
        PagedModel<T> pageList = new PagedModel<>();
        pageList.pageNum = list.getNumber();
        pageList.pageSize = list.getSize();
        pageList.setTotal(list.getTotalElements());
        pageList.setData(list.getContent());
        return pageList;
    }

}
