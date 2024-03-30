package com.Shopping.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "img_id", type = IdType.AUTO)
    private Integer imgId;

    private Integer productId;

    private String productName;

    private String productImage;

    private Integer productPrice;
    private Integer customerId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}
