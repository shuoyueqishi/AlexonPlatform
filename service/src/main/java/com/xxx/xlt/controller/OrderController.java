package com.xxx.xlt.controller;

import com.xxx.xlt.model.*;
import com.xxx.xlt.service.impl.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController(value = "orderController")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/head/list/{pageSize}/{currentPage}", method = RequestMethod.GET, produces = "application/json")
    public CommonResponse<OrderHead> getOrderHeadPageList(@Param("orderHead") OrderHead orderHead, @PathVariable("pageSize") int pageSize, @PathVariable("currentPage") int currentPage) {
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrentPage(currentPage);
        return orderService.findOrderHeadPageList(orderHead, page);
    }

    @RequestMapping(value = "/head/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public CommonResponse<OrderHead> deleteOrderHeadById(@PathVariable("id") int id) {
        return orderService.deleteOrderHead(id);
    }

    @RequestMapping(value = "/head/update", method = RequestMethod.PUT, produces = "application/json")
    public CommonResponse<OrderHead> updateOrderHead(@RequestBody OrderHead orderHead) {
        return orderService.updateOrderHead(orderHead);
    }

    @RequestMapping(value = "/head/add", method = RequestMethod.POST, produces = "application/json")
    public CommonResponse<OrderHead> addOrderHead(@RequestBody() OrderHead orderHead) {
        return orderService.addNewOrderHead(orderHead);
    }

    @RequestMapping(value = "/head/import", method = RequestMethod.POST, produces = "application/json")
    public BasicResponse importOrderHead(MultipartFile file) {
        return orderService.importOrderHead(file);
    }
}
