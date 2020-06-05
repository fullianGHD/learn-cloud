package com.ghd.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 13:55
 */
@Data
public class UserData {

    /**
     * 现在时间
     */
    @ExcelProperty("时间")
    private String nowDate;

    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String username;

    /**
     * 地址
     */
    @ExcelProperty("地址")
    private String address;

    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    private Integer age;
}
