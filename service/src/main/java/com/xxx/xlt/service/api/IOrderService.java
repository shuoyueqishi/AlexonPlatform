package com.xxx.xlt.service.api;

import com.xxx.xlt.model.*;

public interface IOrderService {
    CommonResponse<OrderHead> findOrderHeadPageList(OrderHead orderHead, Page page);

    CommonResponse<OrderHead> deleteOrderHead(int headId);

    CommonResponse<OrderHead> addNewOrderHead(OrderHead orderHead);

    CommonResponse<OrderHead> updateOrderHead(OrderHead orderHead);

    CommonResponse<OrderLine> finOrderLinePageList(OrderLine orderLine, Page page);

    CommonResponse<OrderLine> deleteOrderLine(int lineId);

    CommonResponse<OrderLine> addNewOrderLine(OrderLine orderLine);

    CommonResponse<OrderLine> updateOrderLine(OrderLine orderLine);

}
