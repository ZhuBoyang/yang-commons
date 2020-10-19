package online.yangcloud.utils;

import java.util.List;

/**
 * 分页工具类
 *
 * @author zhuby
 * @date 2020/10/19 9:15 下午
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

    PagerHelper() {
    }

    PagerHelper(Integer offset, Integer count, Long total, List<T> data) {
        this.offset = offset;
        this.count = count;
        this.total = total;
        this.data = data;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
