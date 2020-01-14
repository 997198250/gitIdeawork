package com.fh.util;

import com.fh.common.ExcelFild;
import com.fh.common.ExcleHeard;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExcelUtil {


    //关于excel 了解几个对象
    public static void exceleUtil(List list,HttpServletResponse response) {
    //处理标题
        Object o = list.get(0);//得到要下载的对象
        Class aClass = o.getClass();//得到类对象
        //获得标题名 标题名在我们写的注解里面
        ExcleHeard annotation = (ExcleHeard) aClass.getAnnotation(ExcleHeard.class);//判断类上是否有这个注解
        String title = annotation.title();//得到标题名字
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet(title);//给标题头赋值
        XSSFRow row = sheet.createRow(0);//处理列头

        Field[] declaredFields = aClass.getDeclaredFields();//获取对象的所有属性
        int cellNum=0;
        for (int i = 0; i <declaredFields.length ; i++) {
            Field field=declaredFields[i];//每一个属性
            ExcelFild annotation1 = field.getAnnotation(ExcelFild.class);//属性中注解
            if(annotation1!=null){
                String name = annotation1.name();
                XSSFCell cell = row.createCell(cellNum);//将列名放入具体的列中
                cell.setCellValue(name);//将值放入列中
                cellNum++;
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            Object o1 = list.get(i); //获取具体的数据
            XSSFRow row1 = sheet.createRow(i + 1);//创建行 因为上面有一行了 并且i初始值为0
            int celln=0;
            for (int j = 0; j <declaredFields.length ; j++) {//具体的每一个属性
                Field field=declaredFields[j];
                boolean annotationPresent = field.isAnnotationPresent(ExcelFild.class);
                if(annotationPresent==true){
                    XSSFCell cell = row1.createCell(celln);
                    field.setAccessible(true);//暴力访问
                    try {
                        Object o2 = field.get(o1);
                        if(o2!=null){
                            Class type = field.getType();
                            //判断属性的类型
                            if(type== Date.class){
                                //日期格式化
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                                Date da= (Date) o2;
                                String format = sdf.format(o2);
                                cell.setCellValue(format);
                            }else {
                                cell.setCellValue(o2.toString());
                            }
                        }else {
                            cell.setCellValue("");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    celln++;
                }
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\""+ UUID.randomUUID().toString()+".xlsx\"");

        ServletOutputStream outputStream=null;
        try {
            outputStream=response.getOutputStream();
            book.write(outputStream);
            outputStream.close();
        }catch (Exception e){

        }
    }
}
