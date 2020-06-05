package com.ghd.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghd.model.DemoData;
import com.ghd.model.PreviewClass;
import com.ghd.model.UserData;
import com.ghd.service.ExportExcelService;
import com.ghd.service.impl.CustomSheetWriteHandler;
import com.ghd.service.impl.DemoDataListener;
import com.ghd.service.impl.SpinnerWriteHandler;
import com.ghd.service.impl.TestWriteHandler;
import com.ghd.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 10:29
 */
@RestController
@RequestMapping("/export")
@Slf4j
public class ExportExcelController {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    private ExportExcelService exportExcelService;


    @GetMapping("/title")
    public String title() {
        log.info("---------------導出-----------");
        exportExcelService.exportListTile();
        return "SUCCESS";
    }


    @GetMapping("/newExport")
    public String newExport(HttpServletResponse response) throws IOException {
        log.info("------------测试EasyExport导出-------------------");
        String [] productTypes = new String[]{"吴晓花-Vicky[1]","吴晓花-Vicky[2]","吴晓花-Vicky[3]","吴晓花-Vicky[4]",
                "吴晓花-Vicky[5]","吴晓花-Vicky[6]","吴晓花-Vicky[7]","吴晓花-Vicky[8]","吴晓花-Vicky[9]","吴晓花-Vicky[10]","吴晓花-Vicky[11]","吴晓花-Vicky[12]",
                "吴晓花-Vicky[13]","吴晓花-Vicky[14]","吴晓花-Vicky[15]"};

        //导出
        String filename = URLEncoder.encode("asdasd", "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        String listFileName = "xiala";
        ExcelWriter excelWriter = EasyExcel.write(listFileName).build();
        WriteSheet sheet3 = EasyExcel.writerSheet("sheet3").head(DemoData.class).build();
        excelWriter.write(Arrays.asList(productTypes),sheet3);
        EasyExcel.write(outputStream, PreviewClass.class).registerWriteHandler(new SpinnerWriteHandler(productTypes)).sheet("sheet1").doWrite(new ArrayList());
        return "SUCCESS";
    }


    @PostMapping("/readExcel")
    public String readExcel(@RequestParam("file") MultipartFile file) throws IOException {
       /* if(file.isEmpty()){
            log.info("文件为空");
        }
        String filename = file.getOriginalFilename();
        String newFileName = Long.toString((new Date()).getTime()) + "." + filename.substring(filename.lastIndexOf(".") + 1);;
        File dest = new File(filePath + "/" + newFileName);
        file.transferTo(dest);


        EasyExcel.read(newFileName, PreviewClass.class, new DemoDataListener()).sheet().doRead();*/

        String fileName = "D:\\EXCEL\\AuditionClassTemplate.xlsx";
        List<PreviewClass> previewClass = EasyExcel.read(fileName).head(PreviewClass.class).sheet().doReadSync();
        List<PreviewClass> pList = new ArrayList<PreviewClass>();
        for (PreviewClass p : previewClass) {
            PreviewClass np = new PreviewClass();
            np.setClassDate(p.getClassDate());
            np.setClassDateBegin(p.getClassDateBegin());
            np.setClassDateEnd(p.getClassDateEnd());
            np.setAgeMin(p.getAgeMin());
            np.setAgeMax(p.getAgeMax());
            np.setTeacherMainUid(StringUtils.substringBetween(p.getTeacherMainName(), "[", "]"));
            np.setTeacherMainName(StringUtils.substringBefore(p.getTeacherMainName(),"["));
            np.setTeacherAssistUid(StringUtils.substringBetween(p.getTeacherAssistName(),"[","]"));
            np.setTeacherAssistName(StringUtils.substringBefore(p.getTeacherAssistName(),"["));
            np.setTeacherForeignUid(StringUtils.substringBetween(p.getTeacherForeignName(),"[","]"));
            np.setTeacherForeignName(StringUtils.substringBefore(p.getTeacherForeignName(),"["));
            np.setArrangeTest(p.getArrangeTest());
            np.setArrangeWord(p.getArrangeWord());
            pList.add(np);
        }
        for(PreviewClass pp : pList){
            System.out.println(pp);
        }
        return "SUCCESS";
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception{
        List<DemoData> d = new ArrayList();
        for(int i = 0;i<20;i++){
            DemoData demoData = new DemoData("吴晓花-Vicky[" + i + "]");
            d.add(demoData);
        }

        String fileName="报表";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        List<PreviewClass> studentList=new ArrayList<PreviewClass>();
        PreviewClass p=new PreviewClass();
        p.setId(1);
        p.setClassDateBegin("12:00");
        p.setClassDateEnd("12:30");
        PreviewClass p2 = new PreviewClass();
        p2.setId(2);
        p2.setClassDate(new Date());
        p2.setClassDateEnd("13:00");
        p2.setClassDateBegin("11:00");
        studentList.add(p);
        studentList.add(p2);
        //这里 需要指定写用哪个class去写
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "sheet1").head(PreviewClass.class).build();
        excelWriter.write(studentList, writeSheet);
        writeSheet = EasyExcel.writerSheet(1, "sheet2").head(DemoData.class).build();
        excelWriter.write(d, writeSheet);

        EasyExcel.write(outputStream).registerWriteHandler(new TestWriteHandler(d,"sheet2")).sheet("sheet1").doWrite(new ArrayList());


        //EasyExcel.write(outputStream, PreviewClass.class).registerWriteHandler(new SpinnerWriteHandler(productTypes)).sheet("sheet1").doWrite(new ArrayList());


        //千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

}
