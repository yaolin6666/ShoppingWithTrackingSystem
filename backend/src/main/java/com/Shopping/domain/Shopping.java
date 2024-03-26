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
public class Shopping implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Integer orderDetailId;

    private Integer orderId;

    private Integer customerId;
    private Integer productId;

    private Integer productNum;

    private String productImage;

    private String productName;

    private Integer productPrice;

    @TableField(value = "product_origin")
    private String productColor;

    private String productErm;

    private String address;

    private Long productPhones;

    private String paymentMethod;

    private String productMessage;

    private String shippingUser;

    private Integer orderMoney;

    private Integer productMnum;

    private Integer shippingMoney;

    private Integer paymentMoney;

    private String shippingCompName;
    private Integer discount;
    private Integer teamId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
