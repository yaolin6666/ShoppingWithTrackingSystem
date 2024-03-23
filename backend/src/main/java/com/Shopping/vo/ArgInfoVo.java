package com.Shopping.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

public class ArgInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer arginfoId;

    private Integer productId;

    private String productName;

    private Integer shopId;
    //状态0 未上架 1上架 2售罄
    private Integer status;

    private Integer count;

    private String productImg;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
