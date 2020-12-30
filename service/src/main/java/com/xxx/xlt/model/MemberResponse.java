package com.xxx.xlt.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse extends BasicResponse implements Serializable {
    private static final long serialVersionUID = -245646394425887394L;
    private List<Member> result;
    private Page page;
}
