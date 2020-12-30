package com.xxx.xlt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -7751119896864993134L;
    private Long id;
    private String number;
    private String password;
    private String name;
    private String sex;
    private String email;
    private String telephone;
    private String token;
}
