package com.hawk.example.hellonacos.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hawk
 * @package com.hawk.example.hellonacos.common.entity
 * @desc
 * @date 2021/7/24
 */
@Data
@TableName("trial_activity")
public class TrialActivity implements Serializable {
    private static final long serialVersionUID = -7848196385489941100L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动状态
     */
    private Integer activityStatus;


    private LocalDateTime applyBeginTime;
    private LocalDateTime applyEndTime;
    private LocalDateTime resultPublishTime;
    private LocalDateTime registDeadline;
    private LocalDateTime activityDeadline;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 修改人
     */
    private String modifyBy;
    /**
     * 是否可用 10-可用 20-删除
     */
    private Integer enableFlag;
    /**
     * 版本号--乐观锁预留字段
     */
    private Long version;

}
