package com.hawk.example.test_mybatis_plus_dynamic_table.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.common.entity
 * @desc
 * @date 2021/7/26
 */
@Data
@TableName("trial_activity_mapping_apply_record")
public class TrialActivityMappingApplyRecord implements Serializable {

    private Long id;

    private Long applyTableSuffixId;

    private Long trialActivityIdBegin;

    private Long trialActivityIdEnd;
}
