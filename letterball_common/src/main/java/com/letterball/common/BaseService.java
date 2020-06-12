package com.letterball.common;

import com.letterball.entity.ResponseBase;
import org.springframework.stereotype.Service;

/**
 * 控制器父类，主要封装返回成功和出错的返回
 */
@Service(value = "baseService")
public class BaseService {

    //自定义返回结果集  code 返回信息msg 返回数据
    public ResponseBase setResult(Integer code, String msg, Object data){
        ResponseBase responseBase = new ResponseBase();
        responseBase.setCode(code);
        responseBase.setMessage(msg);
        if (data != null){
            responseBase.setData(data);
        }
        return responseBase;
    }

    /**
     * 定义统一的返回方法
     * @return
     */

    //返回成功
    public ResponseBase setResultSuccess(){
        return setResult(200,"success",null);
    }

    //返回成功 传信息
    public ResponseBase setResultSuccessMsg(String msg){
        return setResult(200,msg,null);
    }

    //返回成功 传数据
    public ResponseBase setResultSuccessData(Object data){
        return setResult(200,"success",data);
    }

    //返回失败
    public ResponseBase setResultError(String msg){
        return setResult(500,msg,null);
    }

    //自定义返回集
    public ResponseBase setResultSuccess(int code,String message, Object data){
        return setResult(code,message,data);
    }



}