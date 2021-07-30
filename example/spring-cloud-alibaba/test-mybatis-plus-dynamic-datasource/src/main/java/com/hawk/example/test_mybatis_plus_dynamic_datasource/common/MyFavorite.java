package com.hawk.example.test_mybatis_plus_dynamic_datasource.common;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_datasource.common
 * @desc
 * @date 2021/7/30
 */
@Data
@TableName("my_favorite")
public class MyFavorite implements Serializable {
    private static final long serialVersionUID = -7848196385459941100L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;
}
