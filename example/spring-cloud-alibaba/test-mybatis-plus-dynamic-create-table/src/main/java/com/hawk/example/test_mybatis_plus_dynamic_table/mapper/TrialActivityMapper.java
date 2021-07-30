package com.hawk.example.test_mybatis_plus_dynamic_table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialActivity;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialApplyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.mapper
 * @desc
 * @date 2021/7/24
 */
@Mapper
public interface TrialActivityMapper extends BaseMapper<TrialActivity> {
    @Select("select * from ${table_name}")
    TrialApplyRecord getTrialApplyRecord(String tableName);

    @Select("select * from ${table_name}")
    TrialApplyRecord getMaxId();
}
