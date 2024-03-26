package com.Shopping.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "refund_id", type = IdType.AUTO)
    private Integer refundId;

    private Integer orderId;

    private Integer customerId;
    private String productErm;

    private String shippingUser;

    private String province;

    private String city;

    private String district;

    private String address;

    private String paymentMethod;

    private BigDecimal orderMoney;

    private BigDecimal shippingMoney;

    private Integer productId;

    private BigDecimal paymentMoney;

    private String shippingCompName;

    private String shippingSn;

    private String productImage;

    @TableField(value = "product_origin")
    private String productColor;

    private String productName;

    private Long productPhones;

    private String productMessage;

    private Integer productPrice;

    private Integer productNum;

    private String refundNr;

    private String refundTx;

    private String refundLs;

    private String refundSm;

    private String refundZt;

    private String refundImg;
    private Integer discount;

    @TableField(value = "shop_id")
    private Integer shopCustomerId;
    private Integer teamId;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
