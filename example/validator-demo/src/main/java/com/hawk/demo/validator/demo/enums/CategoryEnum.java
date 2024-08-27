package com.hawk.demo.validator.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryEnum {
    MOBILE(1, "手机"),
    COMPUTER(2, "电脑"),
    WATER(3, "水"),
    FOOD(4, "食物"),
    ;

    private final Integer code;
    private final String label;
}
