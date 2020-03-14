package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author yinkang
 * @Date 2020/3/14 11:54
 */
@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER= LoggerFactory.getLogger(ExceptionCatch.class);

    //使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点是一旦创建不可改变，并且线程安全
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //使用builder构建一个异常类型和错误代码的异常map
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder=ImmutableMap.builder();

    static {
        //添加一些基础的类型判断
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }

    //捕获自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){
        LOGGER.error("catch exception : {}\r\nextception: ",e.getMessage(),e);
        ResultCode resultCode=e.getResultCode();
        ResponseResult responseResult= new ResponseResult(resultCode);
        return responseResult;
    }


    //不可预知异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e){
        //记录日志
        LOGGER.error("catch exception:{}",e.getMessage());
        if(EXCEPTIONS==null){
            EXCEPTIONS=builder.build();
            //取出该异常对应的异常代码信息
             final ResultCode resultCode = EXCEPTIONS.get(e.getClass());
             final ResponseResult responseResult;
             //判断map中有无此类型异常
             if(resultCode!=null){
                 //有,直接取出该异常所对应的提示代码信息,封装结果
                 responseResult=new ResponseResult(resultCode);
             }else {
                 //没有，给出统一的提示代码信息
                 responseResult=new ResponseResult(CommonCode.SERVER_ERROR);
             }
             return responseResult;
        }
        return null;
    }
}
