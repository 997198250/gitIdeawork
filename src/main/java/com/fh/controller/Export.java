package com.fh.controller;

import com.fh.common.Excel;
import com.fh.entity.po.Student;
import com.fh.service.StudentService;
import com.fh.util.ExceUtil;
import com.fh.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("export")
@CrossOrigin
public class Export {

    private final static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response, XSSFWorkbook workbook){
        List<Student> studentList=studentService.queryStudentAllList();
        Class<Student> studentClass = Student.class;

        Field[] fields = studentClass.getDeclaredFields();
        List<String> li=new ArrayList<>();
        List<String> stu=new ArrayList<>();
        for (Field field:fields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if(annotation!=null){
                li.add(annotation.value());
                stu.add(annotation.name());
            }
        }
        ExceUtil.exceleUtil(studentList,stu,li,response,workbook);
    }

    //导出Excel
    @RequestMapping("downExcel")
    public void downExcel(HttpServletResponse response){
        List<Student> list = studentService.queryStudentAllList();
        ExcelUtil.exceleUtil(list,response);
    }
}
