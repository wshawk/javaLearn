package com.hawk.demo.validator.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ParamB {
    @NotBlank(message = "name is blank")
    private String name;

    @NotEmpty(message = "numList is empty")
    private List<Integer> numList;
}
