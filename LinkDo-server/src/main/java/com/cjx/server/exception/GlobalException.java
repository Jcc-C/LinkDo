package com.cjx.server.exception;

import com.cjx.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 * 控制器增强类，发生异常时，若符合自定义拦截异常类则会被捕获
 */

@RestControllerAdvice
public class GlobalException  {

    @ExceptionHandler({SQLException.class})
    public RespBean mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据，操作失败!");
        }
        return RespBean.error("数据库异常，操作失败!");
    }
}
