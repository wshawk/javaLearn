package com.example.hawk.fastjson;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author hawk
 * @package com.example.hawk.fastjson
 * @desc
 * @date 2022/6/12
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Human {
    private String name;
    private String age;
}
