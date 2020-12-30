package com.xxx.xlt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    private static final long serialVersionUID = -5992046533967757584L;
    private Long id;
    private String cardNumber;
    private String name;
    private String birthday;
    private Long telephoneNumber;
    private int integral;
    private double money;
    private int paymentType;
    private String address;
    private Date createTime;
    private String createBy;
    private Date updateBy;
    private String lastUpdateBy;
}
