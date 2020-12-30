package com.xxx.xlt.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xxx.xlt.model.Member;
import com.xxx.xlt.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;

@DS("db1")
@Mapper
@Component
public interface IMemberDao {
    /**
     * 分页查询member列表
     * @param member 条件入参
     * @param page 分页参数
     * @return 返回查询结果
     * @throws ApplicationException 异常
     */
    List<Member> findMemberPageList(@Param("member") Member member, @Param("page") Page page)throws ApplicationException;

    /**
     * 分页查询时计算总数
     * @param member 条件入参
     * @return 返回总数
     * @throws ApplicationException 运行异常
     */
    int findMemberPageListCount(@Param("member") Member member)throws ApplicationException;

    /**
     * 更具ID删除
     * @param id ID值
     * @return 删除的条数
     * @throws ApplicationException 异常
     */
    int deleteMemberById(@Param("id") int id) throws ApplicationException;

    /**
     * 新增会员
     * @param member 会员信息
     * @return 返回新增数量
     * @throws ApplicationException 异常
     */
    int addMember(@Param("member") Member member) throws ApplicationException;

    /**
     * 更新会员
     * @param member 会员信息
     * @return 返回更新数量
     * @throws ApplicationException 异常
     */
    int updateMember(@Param("member") Member member) throws ApplicationException;

    /**
     * 根据会员卡号和姓名查询
     * @param member 入参
     * @return 返回符合条件的list
     * @throws ApplicationException 异常
     */
    List<Member> findMemberByNumAndName(@Param("member") Member member) throws ApplicationException;
}
