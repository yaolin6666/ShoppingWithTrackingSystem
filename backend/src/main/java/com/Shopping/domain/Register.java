package com.Shopping.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="注册登记", description="注册登记表")
public class Register implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "register_id", type = IdType.AUTO)
    private Integer registerId;

    private Integer accountId;

    private String certificationImg;

    private String img1;
    private String img2;

    private String img3;

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
