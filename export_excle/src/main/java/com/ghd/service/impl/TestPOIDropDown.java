package com.ghd.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/2 14:00
 */
public class TestPOIDropDown {

    public static void main(String[] args) throws Exception {
        // 创建枚举项
        int len = 200;
        String[] datas = new String[len];
        for (int i = 0; i < len; i++) {
            datas[i] = i + "吴晓花-Vicky[003588bfe8ca46cdb507c0592fe24e0a]";
        }

        // ----------------生成--------------------------------
        // 方法一：使用createExplicitListConstraint实现，缺陷为：
        // 只能满足较少枚举项的下拉框，最好不要超过20个，具体个数根据枚举字段长度而定。
        // Workbook workbook = HSSFSetDropDown(datas);
        // Workbook workbook = XSSFSetDropDown(datas);
        // Workbook workbook = SXSSFSetDropDown(datas);
        /*
         * 简单比较HSSF、XSSF、SXSSF： - 由于新的XSSF支持Excel 2007 OOXML（.xlsx）文件是基于XML的，
         * 因此处理它们的内存占用量高于旧版HSSF支持的（.xls）二进制文件。 -
         * SXSSF（3.8-beta3之后支持）在生成非常大的电子表格时使用， 相较于XSSF,其在某个时间点只能访问有限数量的行。
         * http://poi.apache.org/components/spreadsheet/
         */

        // 方法二：使用createFormulaListConstraint实现，其适用于较多枚举项的下拉框，
        // 实现步骤大致为：创建一个隐藏的sheet，并往里放入枚举项，然后在第一个sheet内增加关联关系
        Workbook workbook = XSSFSetDropDownAndHidden(datas);

        // 输出
        FileOutputStream stream = new FileOutputStream("d:\\testDropDown.xlsx");
        workbook.write(stream);
        stream.close();
    }


    /**
     * 使用createFormulaListConstraint实现下拉框
     * @param formulaString
     * @return
     */
    public static Workbook XSSFSetDropDownAndHidden(String[] formulaString) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("下拉列表测试");
        Row r = sheet.createRow(0);

        Cell c = r.createCell(0);
        c.setCellValue("日期");

        Cell c1 = r.createCell(1);
        c1.setCellValue("开始时间");

        Cell c2 = r.createCell(2);
        c2.setCellValue("结束时间");

        Cell c3 = r.createCell(3);
        c3.setCellValue("最小年龄");

        Cell c4 = r.createCell(4);
        c4.setCellValue("最大年龄");

        Cell c5 = r.createCell(5);
        sheet.setColumnWidth(5,12671);
        c5.setCellValue("主教");

        Cell c6 = r.createCell(6);
        sheet.setColumnWidth(6,12671);
        c6.setCellValue("助教");

        Cell c7 = r.createCell(7);
        sheet.setColumnWidth(7,12671);
        c7.setCellValue("外教");

        Cell c8 = r.createCell(8);
        c8.setCellValue("测试安排");

        Cell c9 = r.createCell(9);
        c9.setCellValue("区角安排");

        // 创建sheet，写入枚举项
        Sheet hideSheet = workbook.createSheet("hiddenSheet");
        for (int i = 0; i < formulaString.length; i++) {
            hideSheet.createRow(i).createCell(0).setCellValue(formulaString[i]);
        }
        // 创建名称，可被其他单元格引用
        Name category1Name = workbook.createName();
        category1Name.setNameName("hidden");
        // 设置名称引用的公式
        // 使用像'A1：B1'这样的相对值会导致在Microsoft Excel中使用工作簿时名称所指向的单元格的意外移动，
        // 通常使用绝对引用，例如'$A$1:$B$1'可以避免这种情况。
        // 参考： http://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Name.html
        category1Name.setRefersToFormula("hiddenSheet!" + "$A$1:$A$" + formulaString.length);
        // 获取上文名称内数据
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createFormulaListConstraint("hidden");
        // 设置下拉框位置
        CellRangeAddressList addressList = new CellRangeAddressList(1, 200, 5, 7);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            // 数据校验
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        // 作用在目标sheet上
        sheet.addValidationData(dataValidation);
        // 设置hiddenSheet隐藏
        workbook.setSheetHidden(1, true);
        return workbook;
    }


    /**
     * 使用较早版本的 HSSF用户模型设置表格下拉框 缺陷：下拉框数据量超过一定数量时，系统抛异常。
     *
     * @param formulaString
     *
     */
    public static Workbook HSSFSetDropDown(String[] formulaString) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("下拉列表测试");
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(formulaString);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(0, 200, 0, 0);
        // 数据有效性对象
        DataValidation dataValidation = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(dataValidation);
        return workbook;
    }

    /**
     * 使用 XSSF用户模型设置表格下拉框，多用来处理xlsx后缀的excel 缺陷：下拉框数据量超过一定数量时，文件打不开。
     *
     * @param formulaString
     *
     */
    public static Workbook XSSFSetDropDown(String[] formulaString) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("下拉列表测试");
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(formulaString);
        CellRangeAddressList addressList = null;
        XSSFDataValidation validation = null;
        for (int i = 0; i < 500; i++) {
            addressList = new CellRangeAddressList(i, i, 0, 0);
            validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            // 07默认setSuppressDropDownArrow(true);
            // validation.setSuppressDropDownArrow(true);
            // validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        }
        return workbook;
    }

    /**
     * 使用 SXSSF用户模型设置表格下拉框 缺陷：下拉框数据量超过一定数量时，文件打不开。
     *
     * @param formulaString
     *
     */
    public static Workbook SXSSFSetDropDown(String[] formulaString) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("下拉列表测试");
        // 加载下拉列表内容
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(formulaString);
        // 设置下拉框位置
        CellRangeAddressList addressList = null;
        addressList = new CellRangeAddressList(0, 500, 0, 0);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            // 数据校验
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(dataValidation);
        return workbook;
    }
}
