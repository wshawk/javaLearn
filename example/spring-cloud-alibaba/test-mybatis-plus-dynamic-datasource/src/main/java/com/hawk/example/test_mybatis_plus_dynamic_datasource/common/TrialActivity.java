package com.hawk.example.test_mybatis_plus_dynamic_datasource.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_datasource.common.entity
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

}
