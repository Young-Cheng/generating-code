package com.cheng.build.utils;

import com.cheng.build.common.constants.CommonConstants;

import java.io.Serializable;

/**
 * 响应信息主体
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 返回标记：成功标记=0，失败标记=1
     */
    private int code;
    /**
     * 返回标记：自定义code
     */
    private String businessCode;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static <T> R<T> ok() {
        return restResult((T)null, CommonConstants.SUCCESS, (String)null, (String)null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, (String)null, (String)null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, (String)null, msg);
    }

    public static <T> R<T> ok(T data, String subCode, String msg) {
        return restResult(data, CommonConstants.SUCCESS, subCode, msg);
    }

    public static <T> R<T> failed() {
        return restResult((T)null, CommonConstants.FAIL, (String)null, (String)null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult((T)null, CommonConstants.FAIL, (String)null, msg);
    }

    public static <T> R<T> failed(String subCode, String msg) {
        return restResult((T)null, CommonConstants.FAIL, subCode, msg);
    }

    private static <T> R<T> restResult(T data, int code, String businessCode, String msg) {
        R<T> apiResult = new R<T>();
        apiResult.setCode(code);
        apiResult.setBusinessCode(businessCode);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> RBuilder<T> builder() {
        return new RBuilder<T>();
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", businessCode=" + this.getBusinessCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public R() {
    }

    public R(int code, String businessCode, String msg, T data) {
        this.code = code;
        this.businessCode = businessCode;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public R<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getBusinessCode() {
        return this.businessCode;
    }

    public R<T> setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static class RBuilder<T> {
        private int code;
        private String businessCode;
        private String msg;
        private T data;

        RBuilder() {
        }

        public RBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public RBuilder<T> businessCode(String businessCode) {
            this.businessCode = businessCode;
            return this;
        }

        public RBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public RBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public R<T> build() {
            return new R<T>(this.code, this.businessCode, this.msg, this.data);
        }

        public String toString() {
            return "R.RBuilder(code=" + this.code + ", businessCode=" + this.businessCode + ", msg=" + this.msg + ", data=" + this.data + ")";
        }
    }
}
