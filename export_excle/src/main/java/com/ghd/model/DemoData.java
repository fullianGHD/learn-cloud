package com.ghd.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.naming.Name;
import java.util.Date;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/29 14:05
 */
public class DemoData {

    /*private String string;

    private Date date;

    private Double doubleData;
*/
    @ExcelProperty(value = "名称",index = 0)
    private String accountName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public DemoData(String accountName) {
        this.accountName = accountName;
    }

    public DemoData(){};
}
