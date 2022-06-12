package com.hawk.example.transactional.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hawk
 * @package com.hawk.example.transactional.dao
 * @desc
 * @date 2022/1/18
 */
@TableName("user")
@Data
public class User {
    private Long id;

    private String name;

    private String password;

    private Long enableFlag;

    private LocalDateTime createTime;
}
