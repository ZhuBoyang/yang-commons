package online.yangcloud.utils;

import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * 分页工具类
 *
 * @author zhuby
 * created on 2020/10/19 9:15 下午
 */

public class PagerHelper<T> {

    /**
     * 页码偏移量（当前所在的页码数，比如第1页：offset = 1）
     */
    private Integer offset;

    /**
     * 每页显示的数量
     */
    private Integer count;

    /**
     * 数据总量
     */
    private Long total;

    /**
     * 每页的数据
     */
    private List<T> data;

    public PagerHelper() {
    }

    public PagerHelper<?> setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public PagerHelper<?> setCount(Integer count) {
        this.count = count;
        return this;
    }

    public PagerHelper<?> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public PagerHelper<?> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getCount() {
        return count;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PagerUtilHelper{" +
                "offset=" + offset +
                ", count=" + count +
                ", total=" + total +
                ", data=" + JSONUtil.toJsonStr(data) +
                '}';
    }
}
