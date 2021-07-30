package com.hawk.example.test_mybatis_plus_dynamic_table.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.common.entity
 * @desc
 * @date 2021/7/26
 */
@Data
@TableName("trial_apply_record_1")
public class TrialApplyRecord implements Serializable {
    private static final long serialVersionUID = -7848196385429941100L;
    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
}
