package com.xxx.xlt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHead implements Serializable {
    private static final long serialVersionUID = -4616429795083062747L;
    private Long orderHeadId;
    private String orderDate;
    private String orderNo;
}
