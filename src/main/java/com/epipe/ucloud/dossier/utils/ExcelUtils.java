//package com.epipe.ucloud.crm.utils;
//
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.metadata.Sheet;
//import com.alibaba.excel.metadata.Table;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import com.epipe.ucloud.crm.modules.sys.firstpage.entity.ResultEntity;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ExcelUtils {
//
//    public static void writeExcel(String fileName, List<ResultEntity> list) throws IOException {
//
//        OutputStream out=new FileOutputStream(fileName);
//        ExcelWriter writer=new ExcelWriter(out,ExcelTypeEnum.XLSX);
//        Sheet sheet=new Sheet(1,0);
//        sheet.setSheetName("sheet1");
//
//        Table table=new Table(1);
//        List<List<String>> titles=new ArrayList<>();
//        titles.add(Arrays.asList("公司"));
//        titles.add(Arrays.asList("部门"));
//        titles.add(Arrays.asList("姓名"));
//        titles.add(Arrays.asList("商机数"));
//        table.setHead(titles);
//
//        List<List<String>> resultList=new ArrayList<>();
//        for(ResultEntity e:list){
//            resultList.add(Arrays.asList(e.getCompanyName(),e.getOfficeName(),e.getName(),e.getCount().toString()));
//        }
//
//        writer.write0(resultList,sheet,table);
//        writer.finish();
//
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        List<ResultEntity> list=new ArrayList<>();
//        ResultEntity en=new ResultEntity();
//        en.setCompanyName("xxxx");
//        en.setOfficeName("yyyy");
//        en.setName("zzzz");
//        en.setCount(1000d);
//        list.add(en);
//        writeExcel("d://text.xlsx",list);
//    }
//}
