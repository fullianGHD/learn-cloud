package com.ghd.service.impl;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.ghd.model.DemoData;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/2 11:55
 * 拦截器
 */
public class TestWriteHandler implements SheetWriteHandler {

    private List<DemoData> d;

    private String enumSheetName1;

    public TestWriteHandler(List<DemoData> d, String enumSheetName1){
        this.d = d;
        this.enumSheetName1 = enumSheetName1;
    }



    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
    }



    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // sheet2里面下拉框对应的一列需要放多少行数据
        int TotalRowCount=d.size();
        // 下拉框数据所在的sheet页名称
        String enumSheetName = enumSheetName1;
        // 下拉框的数据
        String[] operationCity = {};
        if(d.size() > 0 ){
            operationCity = new String[d.size()];
            for(int i= 0; i< d.size(); i++){
                operationCity[i] = d.get(i).getAccountName();
            }
        }
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList cellRangeAddressList5 = new CellRangeAddressList(0, TotalRowCount, 0, 0);
        String cityEnum = "=" + enumSheetName + "!$A$2:$A$" + (operationCity.length + 1);
        DataValidationConstraint constraint2 = helper.createFormulaListConstraint(cityEnum);
        DataValidation dataValidation2 = helper.createValidation(constraint2, cellRangeAddressList5);
        writeSheetHolder.getSheet().addValidationData(dataValidation2);
    }
}
