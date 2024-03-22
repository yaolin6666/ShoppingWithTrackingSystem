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
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "share_id", type = IdType.AUTO)
    private Integer shareId;
    private String shareTitle;
    private String shareContent;
    private String shareImg1;
    private String shareImg2;
    private String shareImg3;
    private String shareImg4;
    private String shareImg5;
    private String shareImg6;
    private String shareImg7;
    private String shareImg8;
    private String shareImg9;
    private String shareVideo;
    private Integer productId;
    private Integer customerId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
