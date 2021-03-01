package com.xxx.xlt.controller;

import com.xxx.xlt.constant.Constant;
import com.xxx.xlt.model.BasicResponse;
import com.xxx.xlt.service.mq.producer.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class MqController {
    @Autowired
    private MqSender mqSender;

    @RequestMapping(value = "/topic", method = RequestMethod.POST, produces = "application/json")
    public BasicResponse topicMq(@RequestBody String msg) {
        mqSender.sendTopic(msg);
        return new BasicResponse("send topic exchange mq success","200", Constant.Status.SUCCESS);
    }

    @RequestMapping(value = "/direct", method = RequestMethod.POST, produces = "application/json")
    public BasicResponse directMq(@RequestBody String msg) {
        mqSender.sendDirect(msg);
        return new BasicResponse("send direct exchange mq success","200", Constant.Status.SUCCESS);
    }

}
