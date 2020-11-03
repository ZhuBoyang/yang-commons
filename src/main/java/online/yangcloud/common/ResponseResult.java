package online.yangcloud.common;

import cn.hutool.http.HttpStatus;

/**
 * @author zhuby
 * @since 2020/11/2 10:56 上午
 */

public class ResponseResult {

    private static final int HTTP_EXCEPTION = 555;

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    /**
     * 不使用
     */
    private String ok;

    public static ResponseResult build(Integer status, String msg, Object data) {
        return new ResponseResult(status, msg, data);
    }

    public static ResponseResult build(Integer status, String msg, Object data, String ok) {
        return new ResponseResult(status, msg, data, ok);
    }

    public static ResponseResult ok(String msg, Object data) {
        return new ResponseResult(HttpStatus.HTTP_OK, msg, data);
    }

    public static ResponseResult ok() {
        return new ResponseResult("");
    }

    public static ResponseResult errorMsg(String msg) {
        return new ResponseResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, "");
    }

    public static ResponseResult errorMap(Object data) {
        return new ResponseResult(HttpStatus.HTTP_NOT_IMPLEMENTED, "error", data);
    }

    public static ResponseResult errorTokenMsg(String msg) {
        return new ResponseResult(HttpStatus.HTTP_BAD_GATEWAY, msg, "");
    }

    public static ResponseResult errorException(String msg) {
        return new ResponseResult(HTTP_EXCEPTION, msg, "");
    }

    private ResponseResult() {

    }

    private ResponseResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ResponseResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    private ResponseResult(Object data) {
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }

    private Boolean isOk() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    private void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    private void setOk(String ok) {
        this.ok = ok;
    }

}
