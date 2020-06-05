package com.ghd.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ghd.model.DemoData;
import com.ghd.model.PreviewClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/29 14:06
 */
@Slf4j
public class DemoDataListener extends AnalysisEventListener<PreviewClass> {

    List<PreviewClass> list = new ArrayList<>();


    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param context
     */
    @Override
    public void invoke(PreviewClass data, AnalysisContext context) {
        log.info("解析到一条数据:{}");
        System.out.println(data);
        list.add(data);
        if(list.size() > 1000){
            saveData();
            //存储完成后清理list
            list.clear();
        }
    }


    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }


    private void saveData(){
        log.info("excel文件超过1000条进行一次持久化");
    }



}
