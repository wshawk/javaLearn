package com.hawk.demo.validator.demo.model;

import com.hawk.demo.validator.demo.enums.CategoryEnum;
import com.hawk.demo.validator.demo.validator.EnumValid;
import com.hawk.demo.validator.demo.validator.ValidGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Param {
//    @NotNull(message = "ID不能为空", groups = {ValidGroup.A.class})
    private Long id;

//    @NotBlank(message = "名称不能为空", groups = {ValidGroup.A.class, ValidGroup.C.class})
    private String name;

//    @NotNull(message = "数量不能为空", groups = {ValidGroup.B.class, ValidGroup.C.class})
    private Integer num;

//    @EnumValid(enumClass = CategoryEnum.class, method = "getLabel", message = "类型不正确", groups = {ValidGroup.B.class, ValidGroup.E.class})
    private String category;

//    @NotEmpty(message = "bs is empty")

    private List<@Valid ParamB> bs;

    public Class<?>[] getAddValidationGroups() {
        List<Class<?>> validationGroups = new ArrayList<>();
        validationGroups.add(Default.class);
        validationGroups.add(ValidGroup.A.class);

        if (Objects.equals(CategoryEnum.WATER.getLabel(), category)) {
            validationGroups.add(ValidGroup.E.class);
            validationGroups.remove(ValidGroup.A.class);
        }

        return validationGroups.toArray(new Class<?>[0]);
    }
}
