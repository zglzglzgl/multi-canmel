package com.core.entity;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class Result extends HttpResult {

    public static final String NOT_FOUND_MSG = "未找到请求页面";

    public Integer httpStatus;
    public String msg;
    public String access_token;
    public Object data;

    public Result(Integer httpStatus, String access_token) {
        this.httpStatus = httpStatus;
        this.access_token = access_token;
    }

    public Result(Integer httpStatus, Object data) {
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public Result(Integer httpStatus, String msg, String access_token) {
        this.httpStatus = httpStatus;
        this.msg = msg;
        this.access_token = access_token;
    }

    public Result(Integer httpStatus, String msg, String access_token, Object data) {
        this.httpStatus = httpStatus;
        this.msg = msg;
        this.access_token = access_token;
        this.data = data;
    }

    public Result() {
    }

    public static Result OK(String access_token) {
        return new Result(HttpStatus.OK.value(), access_token);
    }

    public static Result OK(HttpServletRequest request, Object data) {
        String access_token = "";
        if(!ObjectUtils.isEmpty(request.getSession().getAttribute("ACCESS_TOKEN"))){
            access_token = request.getSession().getAttribute("ACCESS_TOKEN").toString();
        }
        return new Result(HttpStatus.OK.value(), "请求成功", access_token, data);
    }

    public static Result OK(String access_token, String msg) {
        return new Result(HttpStatus.OK.value(), msg, access_token);
    }

    public static Result OK(String access_token, String msg, Object obj) {
        return new Result(HttpStatus.OK.value(), msg, access_token, obj);
    }

    public static Result OK(Object data) {
        return new Result(HttpStatus.OK.value(), "请求成功", null, data);
    }


}
