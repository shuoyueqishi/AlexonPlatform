package com.xxx.xlt.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xxx.xlt.model.Member;
import com.xxx.xlt.model.OrderHead;
import com.xxx.xlt.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;

@DS("db2")
@Mapper
@Component
public interface IOrderHeadMapper {
    /**
     * 分页查询member列表
     * @param page 分页参数
     * @return 返回查询结果
     */
    List<OrderHead> findOrderHeadPageList(@Param("vo") OrderHead orderHead, @Param("page") Page page);

    /**
     * 分页查询时计算总数
     * @param orderHead 条件入参
     * @return 返回总数
     */
    int findOrderHeadPageListCount(@Param("vo") OrderHead orderHead);
}
