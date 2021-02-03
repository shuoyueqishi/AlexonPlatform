package com.xxx.xlt.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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

    @ExcelProperty(value="订单ID", index = 0)
    @ColumnWidth(15)
    private Long orderHeadId;

    @ExcelProperty(value="下单日期", index = 2)
    @ColumnWidth(20)
    private String orderDate;

    @ExcelProperty(value="订单号", index = 1)
    @ColumnWidth(10)
    private String orderNo;
}
