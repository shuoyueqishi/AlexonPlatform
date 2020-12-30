package com.xxx.xlt.service.api;

import com.xxx.xlt.model.Member;
import com.xxx.xlt.model.MemberResponse;
import com.xxx.xlt.model.Page;

public interface IMemberService {
    MemberResponse findMemberPageList(Member member, Page page);

    MemberResponse deleteMember(int id);

    MemberResponse addNewMember(Member member);

    MemberResponse updateMember(Member member);
}
