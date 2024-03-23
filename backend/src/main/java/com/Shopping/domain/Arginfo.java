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
public class Arginfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "arginfo_id", type = IdType.AUTO)
    private Integer arginfoId;

    private Integer productId;

    private Integer shopId;
    //状态0 未上架 1上架 2售罄
    private Integer status;

    private Integer count;

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
