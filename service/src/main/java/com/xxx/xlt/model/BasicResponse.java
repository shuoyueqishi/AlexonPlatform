package com.xxx.xlt.model;

import com.xxx.xlt.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse implements Serializable {
    private static final long serialVersionUID = 3133901045675557228L;
    private String message;
    private String code;
    private Constant.Status status;
}
