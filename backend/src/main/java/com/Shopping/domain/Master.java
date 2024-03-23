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
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private String orderSn;

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

    private Date shippingTime;

    private Date receiveTime;

    private String productImage;

    private String productColor;

    private String productName;

    private Long productPhones;

    private String productMessage;

    private Integer productPrice;

    private Integer productNum;

    /**
     * 状态 100表示单独购买未发货 180表示团购未达成人数订单 1000失效订单
     * 200表示已发货未收货
     * 300表示已收货
     */
    private Integer status;

    //表示优惠比率 整数数值
    private Integer discount;

    @TableField(value = "shop_id")
    private Integer shopCustomerId;

    private Integer teamId;

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
