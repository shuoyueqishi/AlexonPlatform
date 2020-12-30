package com.xxx.xlt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    private Long orderLineId;
    private Long orderHeadId;
    private String orderDate;
    private String commodity;
    private String customer;
}
