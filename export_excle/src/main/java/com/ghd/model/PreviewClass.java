package com.ghd.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 10:03
 */

public class PreviewClass implements Serializable {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "日期",index = 0)
    private Date classDate;

    @ExcelProperty(value = "开始时间",index = 1)
    private String classDateBegin;

    @ExcelProperty(value = "结束时间",index = 2)
    private String classDateEnd;

    @ExcelProperty(value = "最小年龄",index = 3)
    private Integer ageMin;

    @ExcelProperty(value = "最大年龄",index = 4)
    private Integer ageMax;

    @ExcelIgnore
    private String teacherMainUid;

    @ExcelProperty(value = "主教",index = 5)
    private String teacherMainName;

    @ExcelIgnore
    private String teacherAssistUid;

    @ExcelProperty(value = "助教",index = 6)
    private String teacherAssistName;

    @ExcelIgnore
    private String teacherForeignUid;

    @ExcelProperty(value = "外教",index = 7)
    private String teacherForeignName;

    @ExcelProperty(value = "测试安排",index = 8)
    private String arrangeTest;

    @ExcelProperty(value = "区角安排",index = 9)
    private String arrangeWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public String getClassDateBegin() {
        return classDateBegin;
    }

    public void setClassDateBegin(String classDateBegin) {
        this.classDateBegin = classDateBegin;
    }

    public String getClassDateEnd() {
        return classDateEnd;
    }

    public void setClassDateEnd(String classDateEnd) {
        this.classDateEnd = classDateEnd;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public String getTeacherMainUid() {
        return teacherMainUid;
    }

    public void setTeacherMainUid(String teacherMainUid) {
        this.teacherMainUid = teacherMainUid;
    }

    public String getTeacherMainName() {
        return teacherMainName;
    }

    public void setTeacherMainName(String teacherMainName) {
        this.teacherMainName = teacherMainName;
    }

    public String getTeacherAssistUid() {
        return teacherAssistUid;
    }

    public void setTeacherAssistUid(String teacherAssistUid) {
        this.teacherAssistUid = teacherAssistUid;
    }

    public String getTeacherAssistName() {
        return teacherAssistName;
    }

    public void setTeacherAssistName(String teacherAssistName) {
        this.teacherAssistName = teacherAssistName;
    }

    public String getTeacherForeignUid() {
        return teacherForeignUid;
    }

    public void setTeacherForeignUid(String teacherForeignUid) {
        this.teacherForeignUid = teacherForeignUid;
    }

    public String getTeacherForeignName() {
        return teacherForeignName;
    }

    public void setTeacherForeignName(String teacherForeignName) {
        this.teacherForeignName = teacherForeignName;
    }

    public String getArrangeTest() {
        return arrangeTest;
    }

    public void setArrangeTest(String arrangeTest) {
        this.arrangeTest = arrangeTest;
    }

    public String getArrangeWord() {
        return arrangeWord;
    }

    public void setArrangeWord(String arrangeWord) {
        this.arrangeWord = arrangeWord;
    }

    public PreviewClass() {
    }

    public PreviewClass(Integer id, Date classDate, String classDateBegin, String classDateEnd, Integer ageMin, Integer ageMax, String teacherMainUid, String teacherMainName, String teacherAssistUid, String teacherAssistName, String teacherForeignUid, String teacherForeignName, String arrangeTest, String arrangeWord) {
        this.id = id;
        this.classDate = classDate;
        this.classDateBegin = classDateBegin;
        this.classDateEnd = classDateEnd;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.teacherMainUid = teacherMainUid;
        this.teacherMainName = teacherMainName;
        this.teacherAssistUid = teacherAssistUid;
        this.teacherAssistName = teacherAssistName;
        this.teacherForeignUid = teacherForeignUid;
        this.teacherForeignName = teacherForeignName;
        this.arrangeTest = arrangeTest;
        this.arrangeWord = arrangeWord;
    }
}
