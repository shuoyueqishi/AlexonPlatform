package com.xxx.xlt.model;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty("订单ID")
    private Long orderHeadId;
    @ExcelProperty("下单日期")
    private String orderDate;
    @ExcelProperty("订单号")
    private String orderNo;
}
