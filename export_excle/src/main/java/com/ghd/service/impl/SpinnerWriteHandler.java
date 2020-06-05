package com.ghd.service.impl;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 14:55
 */
@Slf4j
public class SpinnerWriteHandler implements SheetWriteHandler {

    private String[] params;

    public SpinnerWriteHandler(String[] params) {
        this.params = params;
    }


    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Map<Integer,String []> mapDropDown = new HashMap<>(4);
        mapDropDown.put(5,params);
        mapDropDown.put(6,params);
        mapDropDown.put(7,params);
        Sheet sheet = writeSheetHolder.getSheet();
        ///开始设置下拉框、设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();
        for (Map.Entry<Integer, String[]> entry : mapDropDown.entrySet()) {
            /***起始行、终止行、起始列、终止列**/
            CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, entry.getKey(), entry.getKey());
            /***设置下拉框数据**/
            DataValidationConstraint constraint = helper.createExplicitListConstraint(entry.getValue());
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            /***处理Excel兼容性问题**/
            if (dataValidation instanceof XSSFDataValidation) {
                dataValidation.setSuppressDropDownArrow(true);
                dataValidation.setShowErrorBox(true);
            } else {
                dataValidation.setSuppressDropDownArrow(false);
            }
            sheet.addValidationData(dataValidation);
        }

        /**
         * int TotalRowCount=88; // sheet2里面下拉框对应的一列需要放多少行数据
         * String enumSheetName = "sheet2"; // 下拉框数据所在的sheet页名称
         * String[] operationCity; // 下拉框的数据
         * CellRangeAddressList cellRangeAddressList5 = new CellRangeAddressList(1, TotalRowCount, 5, 5);
         * String cityEnum = "=" + enumSheetName + "!$A$2:$A$" + (operationCity.length + 1);
         * DataValidationConstraint constraint2 = helper.createFormulaListConstraint(cityEnum);
         * DataValidation dataValidation2 = helper.createValidation(constraint2, cellRangeAddressList5);
         * writeSheetHolder.getSheet().addValidationData(dataValidation2);
         */

    }


}
