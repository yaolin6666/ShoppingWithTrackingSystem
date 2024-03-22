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
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "team_id", type = IdType.AUTO)
    private Integer teamId;
    private Integer productId;
    private Integer shopId;
    private Integer teamNow;
    private Integer teamMax;
    private Integer teamNeed;
    private String teamAttendId;
    private String teamAttendName;
    private Integer templateId;
    private Integer discount;
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
