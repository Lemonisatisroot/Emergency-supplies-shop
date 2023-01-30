package com.apple.emergency.controller;

import com.apple.emergency.service.MqttMessageCallback;
import com.apple.emergency.utils.MqttClientUtil;
import com.apple.emergency.vo.Result;
import com.apple.emergency.vo.params.SendMsgParams;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HASEE
 * @title MqttSendMessageController
 * @date 2022/9/20 20:10
 * @description TODO
 */
@Slf4j
@RestController
@RequestMapping("mqtt")
public class MqttSendMessageController {

    @Autowired
    private MqttClientUtil mqttClientUtil;

    @Autowired
    private MqttMessageCallback mqttMessageCallback;

    /**
     * 发布主题消息
     * @param sendMsgParams
     * @return
     */
    @PostMapping("send")
    public Result sendMessage(@RequestBody SendMsgParams sendMsgParams){
        String topic = "Apple-Lemon-Sub-98:CD:AC:0E:6C:1E";
        //String message = "1";
        String message = sendMsgParams.getMessage();
        //0:最多一次 1：最少一次 2：至少一次
        int qos = 2;
        try{
            mqttClientUtil.sendMessage(topic,message,qos);
        }catch(Exception e){
            log.error("mqtt 消息发送异常",e);
        }
        log.info("mqtt 消息已发送！");
        return Result.success(null);
    }

}
