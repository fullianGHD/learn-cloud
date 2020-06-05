package com.ghd.service.impl;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Service;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 14:20
 */
@Service
@Slf4j
public class CustomSheetWriteHandler implements SheetWriteHandler {


    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        log.info("第{}个Sheet写入成功。", writeSheetHolder.getSheetNo());


        // 区间设置 第一列第一行和第二行的数据。由于第一行是头，所以第一、二行的数据实际上是第二三行

        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, 2, 0, 0);

        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();

        DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[] {"测试1", "测试2"});

        DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);

        writeSheetHolder.getSheet().addValidationData(dataValidation);
    }
}
