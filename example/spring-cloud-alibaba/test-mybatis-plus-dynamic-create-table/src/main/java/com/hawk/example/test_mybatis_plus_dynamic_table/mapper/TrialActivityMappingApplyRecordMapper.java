package com.hawk.example.test_mybatis_plus_dynamic_table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivityMappingApplyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.mapper
 * @desc
 * @date 2021/7/24
 */
@Mapper
public interface TrialActivityMappingApplyRecordMapper extends BaseMapper<TrialActivityMappingApplyRecord> {

    @Select("select * from trial_activity_mapping_apply_record order by id desc limit 1")
    TrialActivityMappingApplyRecord getLastRecord();
}
