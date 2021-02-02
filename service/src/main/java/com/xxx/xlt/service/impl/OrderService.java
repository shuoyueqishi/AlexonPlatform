package com.xxx.xlt.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.xxx.xlt.constant.Constant;
import com.xxx.xlt.excel.imports.OrderHeadListener;
import com.xxx.xlt.mapper.IOrderHeadMapper;
import com.xxx.xlt.model.*;
import com.xxx.xlt.service.api.IOrderService;
import com.xxx.xlt.utils.common.SnowflakeIdGenerator;
import com.xxx.xlt.utils.exception.CommonException;
import com.xxx.xlt.utils.idempotent.method2.Idempotent;
import com.xxx.xlt.utils.redis.RedisCacheable;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@MapperScan("com.xxx.xlt.mapper")
public class OrderService implements IOrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private IOrderHeadMapper orderHeadMapper;

    @Override
    @RedisCacheable(key="'com.xxx.xlt:'.concat(#orderHeadId).concat(#orderDate).concat(#orderNo)",firstLayerTtl=20,secondLayerTtl = 3)
//    @RedisCacheable(key="'com.xxx.xlt:'.concat(#orderDate)",firstLayerTtl=20,secondLayerTtl = 3)
    public CommonResponse<OrderHead> findOrderHeadPageList(OrderHead orderHead, Page page) {
        CommonResponse<OrderHead> response = new CommonResponse<>();
        logger.info("OrderService.findOrderHeadPageList input info:"+orderHead);
        int totals = orderHeadMapper.findOrderHeadPageListCount(orderHead);
        page.setTotal(totals);
        int totalPages = page.getTotalPages();
        logger.info("totalPages="+totalPages);
        List<OrderHead> list = orderHeadMapper.findOrderHeadPageList(orderHead,page);
        response.setData(list);
        response.setPage(page);
        response.setCode("success");
        response.setStatus(Constant.Status.SUCCESS);
        response.setMessage(list.size()+" orderHead infos has found in this query.");
        return response;
    }

    @Override
    public CommonResponse<OrderHead> deleteOrderHead(int headId) {
        return null;
    }

    @Override
    @Idempotent(value="OrderService.addNewOrderHead",expireMillis=100L)
    public CommonResponse<OrderHead> addNewOrderHead(OrderHead orderHead) {
        CommonResponse<OrderHead> response = new CommonResponse<>();
        if (StringUtils.isEmpty(orderHead.getOrderDate())) {
            throw new CommonException("orderDate is empty.");
        }
        if (StringUtils.isEmpty(orderHead.getOrderNo())) {
            throw new CommonException("orderNo is empty.");
        }
        Long orderId = SnowflakeIdGenerator.generateId();
        orderHead.setOrderHeadId(orderId);
        orderHeadMapper.insertOrderHead(orderHead);
        response.setData(Collections.singletonList(orderHead));
        return response;
    }

    @Override
    public BasicResponse importOrderHead(MultipartFile file) {
        BasicResponse response = new BasicResponse();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            EasyExcel.read(inputStream, OrderHead.class,new OrderHeadListener(orderHeadMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public BasicResponse exportOrderHead(OrderHead orderHead) {
        BasicResponse response = new BasicResponse();
        String fileName =  "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, OrderHead.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        return response;
    }

    @Override
    public CommonResponse<OrderHead> updateOrderHead(OrderHead orderHead) {
        return null;
    }

    @Override
    public CommonResponse<OrderLine> finOrderLinePageList(OrderLine orderLine, Page page) {
        return null;
    }

    @Override
    public CommonResponse<OrderLine> deleteOrderLine(int lineId) {
        return null;
    }

    @Override
    public CommonResponse<OrderLine> addNewOrderLine(OrderLine orderLine) {
        return null;
    }

    @Override
    public CommonResponse<OrderLine> updateOrderLine(OrderLine orderLine) {
        return null;
    }
}
