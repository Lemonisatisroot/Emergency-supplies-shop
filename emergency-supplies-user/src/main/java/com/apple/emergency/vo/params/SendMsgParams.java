package com.apple.emergency.vo.params;

import lombok.Data;

/**
 * @author HASEE
 * @title SendMsgParams
 * @date 2022/9/20 20:43
 * @description TODO
 */
@Data
public class SendMsgParams {

    private String message;

    private String topic;

    private int qos;
}
