package com.apple.emergency.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author HASEE
 * @title MqttMessageCallback
 * @date 2022/9/20 8:53
 * @description TODO
 */
@Component
@Slf4j
public class MqttMessageCallback implements MqttCallback {


    /**
     * 链接丢失时处理
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        //可以做重连 或者 其他业务处理
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        log.info("接收到消息topic---->{}",topic);
        log.info("接收到消息质量qos---->{}",mqttMessage.getQos());
        log.info("接收到消息具体信息---->{}",new String(mqttMessage.getPayload()));
        //结合业务 编写具体信息即可

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

