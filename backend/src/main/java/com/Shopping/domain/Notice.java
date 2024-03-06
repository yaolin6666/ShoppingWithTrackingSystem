package com.Shopping.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_notice")
public class Notice extends Model<Notice> {

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    private String adminName;

    private String adminPwd;
    private String productErm;

    private String adminPhone;

    private Integer adminState;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String adminImage;

    private String adminMessage;
    private String adminAddress;
    private String adminDescribe;
    private Long adminPhones;
    private String adminPay;


}
