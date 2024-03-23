package com.Shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String argInfoID;
    private String argId;
    private String argInfo;
    private String extraArgInfo;
}
