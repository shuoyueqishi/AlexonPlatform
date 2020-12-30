package com.xxx.xlt.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BasicResponse implements Serializable {

    private static final long serialVersionUID = -1888884229708776345L;

    private List<T> data;
    private Page page;
}
