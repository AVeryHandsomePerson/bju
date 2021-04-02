package com.cn.bju.spring.bigdataspringboot.bean.platform;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:27
 */
public class PagerBean {
    private int page;//分页起始页
    private int size;//每页记录数
    private Object rows;//返回的记录集合
    private long total;//总记录条数
    private int code;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PagerBean{" +
                "page=" + page +
                ", size=" + size +
                ", rows=" + rows +
                ", total=" + total +
                ", code=" + code +
                '}';
    }
}
