package com.cjx.server.utils;

import com.cjx.server.exception.ParamsException;

public class AssertUtil {


    /**
     * 判断条件是否满足
     *  如果条件满足，则抛出参数异常
     */
    public  static void isTrue(Boolean flag, String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
