package com.hawk.example.test_mybatis_plus_dynamic_datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.test_mybatis_plus_dynamic_datasource.common.MyFavorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_datasource.mapper
 * @desc
 * @date 2021/7/30
 */
@Mapper
public interface MyFavoriteMapper extends BaseMapper<MyFavorite> {
}
