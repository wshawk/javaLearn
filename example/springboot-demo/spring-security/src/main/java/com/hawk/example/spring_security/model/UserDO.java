package com.hawk.example.spring_security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hawk
 * @package com.hawk.example.spring_security_start.model
 * @desc
 * @date 2021/10/25
 */
@Data
@AllArgsConstructor
// @TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 4479131972139331820L;

    private Long id;

    private String username;

    private String password;

    private String realName;

    private List<String> roles;

    private List<String> permissions;
}
