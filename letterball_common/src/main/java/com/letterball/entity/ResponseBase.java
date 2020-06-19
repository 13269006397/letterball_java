package com.letterball.entity;

import lombok.Data;

/**
 * 接口返回 响应对象
 */
@Data
public class ResponseBase {

    private Integer code;   //返回码
    private String msg; //返回信息
    private Object data;    //返回数据
}
