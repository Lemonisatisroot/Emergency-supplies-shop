package com.apple.emergency.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HASEE
 * @title Result
 * @date 2022/8/23 15:17
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;


    public static Result success(Object data){

        return new Result(true,200,"success",data);
    }

    public static Result fail(int code,String msg){

        return new Result(false,code,msg,null);
    }
}
