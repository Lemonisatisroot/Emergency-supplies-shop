package com.apple.emergency.utils;

import com.apple.emergency.service.MqttMessageCallback;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @author HASEE
 * @title MqttClientUtil
 * @date 2022/9/20 8:51
 * @description TODO
 */
@Component
@Slf4j
public class MqttClientUtil {
    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.url}")
    private String host;
    @Value("${mqtt.client.id}")
    private String clientId;
    @Value("${mqtt.topic}")
    private String topic;
    @Value("${mqtt.connection.timeout}")
    private int timeOut;
    @Value("${mqtt.keep.alive.interval}")
    private int interval;

    @Autowired
    private MqttMessageCallback mqttMessageCallback;

    private MqttClient mqttClient;

    private MqttConnectOptions mqttConnectOptions;

    @PostConstruct
    private void init(){
        connect(host, clientId,topic);
    }

    /**
     * 链接mqtt
     * @param host
     * @param clientId
     */
    private void connect(String host,String clientId,String topic){
        try{
            mqttClient = new MqttClient(host,clientId,new MemoryPersistence());
            mqttConnectOptions = getMqttConnectOptions();
            //设置回调函数
            mqttClient.setCallback(mqttMessageCallback);
            //链接mqtt
            mqttClient.connect(mqttConnectOptions);
            //订阅消息
            mqttClient.subscribe(topic,2);
        }catch (Exception e){
            log.error("mqtt服务链接异常!");
            e.printStackTrace();
        }
    }

    /**
     * 设置链接对象信息
     * setCleanSession  true 断开链接即清楚会话  false 保留链接信息 离线还会继续发消息
     * @return
     */
    private MqttConnectOptions getMqttConnectOptions(){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{host});
        mqttConnectOptions.setKeepAliveInterval(interval);
        mqttConnectOptions.setConnectionTimeout(timeOut);
        mqttConnectOptions.setCleanSession(true);
        return mqttConnectOptions;
    }

    /**
     *mqtt链接状态
     * @return
     */
    private boolean isConnect(){
        if(Objects.isNull(this.mqttClient)){
            return false;
        }
        return mqttClient.isConnected();
    }

    /**
     * 设置重连
     * @throws Exception
     */
    private void reConnect() throws Exception{
        if(Objects.nonNull(this.mqttClient)){
            log.info("mqtt 服务已重新链接...");
            this.mqttClient.connect(this.mqttConnectOptions);
        }
    }

    /**
     * 断开链接
     * @throws Exception
     */
    private void closeConnect() throws Exception{
        if(Objects.nonNull(this.mqttClient)){
            log.info("mqtt 服务已断开链接...");
            this.mqttClient.disconnect();
        }
    }

    /**
     * 发布消息
     * @param topic
     * @param message
     * @param qos
     * @throws Exception
     */
    public void sendMessage(String topic,String message,int qos) throws Exception {
        if(Objects.nonNull(this.mqttClient) && this.mqttClient.isConnected()){
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message.getBytes());
            mqttMessage.setQos(qos);

            MqttTopic mqttTopic = mqttClient.getTopic(topic);

            if(Objects.nonNull(mqttTopic)){
                try{
                    MqttDeliveryToken publish = mqttTopic.publish(mqttMessage);
                    if(publish.isComplete()){
                        log.info("消息发送成功---->{}",message);
                    }
                }catch(Exception e){
                    log.error("消息发送异常",e);
                }
            }
        }else{
            reConnect();
        }
    }


}

