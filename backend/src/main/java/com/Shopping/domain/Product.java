package com.Shopping.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
//import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    private String productCode;

    private String productName;
//描述图片
    private String productMd;
    private String productMf;
    private String productMg;
//二维码
    private String productErm;

    private String brandId;

    private Integer customerId;

    private  Integer productPrice;

    private String weight;

    private String descript;

    private String productHome;

    private String productColor;

    private String productNum;

    private String productImage;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;




}
