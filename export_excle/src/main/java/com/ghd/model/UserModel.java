package com.ghd.model;

import com.ghd.annocation.ExplicitConstraint;
import com.ghd.service.impl.RoleNameExplicitConstraint;
import lombok.Data;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 11:29
 */
@Data
public class UserModel {

    private String name;

    private Integer age;

    /**
     * 固定下拉内容
     */
    @ExplicitConstraint(source = {"男","女"})
    private String sex;

    /**
     * 动态下拉内容
     */
    @ExplicitConstraint(sourceClass = RoleNameExplicitConstraint.class)
    private String roleName;

}
