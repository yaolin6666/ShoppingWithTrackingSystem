package com.Shopping.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = " receipt_id", type = IdType.AUTO)
    private Integer  receiptId;

    private Integer customerId;

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

    private BigInteger shippingSn;

    private String productImage;

    private String productColor;

    private String productName;

    private Long productPhones;

    private String productMessage;

    private Integer productPrice;

    private Integer productNum;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Date shippingTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date receiveTime;


}
