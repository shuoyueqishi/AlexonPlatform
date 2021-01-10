package com.xxx.xlt.controller;

import com.xxx.xlt.constant.Constant;
import com.xxx.xlt.model.BasicResponse;
import com.xxx.xlt.utils.idempotent.AutoIdempotent;
import com.xxx.xlt.utils.idempotent.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口幂等
 */
@RestController
@RequestMapping("/idempotent")
public class BusinessController {


    @Autowired
    private ITokenService tokenService;

    @GetMapping("/get/token")
    public Object  getToken(){
        String token = tokenService.createToken();
        return  new BasicResponse(token,"200", Constant.Status.SUCCESS);
    }


    @AutoIdempotent
    @GetMapping("/test")
    public Object testIdempotence() {
        String token = "接口幂等性测试成功";
        return new BasicResponse(token,"200", Constant.Status.SUCCESS);
    }
}
