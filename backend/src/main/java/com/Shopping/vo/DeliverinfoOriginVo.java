package com.Shopping.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverinfoOriginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Id;
    private String deliverId;
    private String deliverInfo;
    private String mediaInfo;
}
