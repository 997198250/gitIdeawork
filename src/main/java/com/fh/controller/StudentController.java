package com.fh.controller;

import com.fh.entity.po.Student;
import com.fh.entity.vo.DataUtil;
import com.fh.entity.vo.SearchUtil;
import com.fh.service.StudentService;
import com.fh.util.FileUtilesalbb;
import com.fh.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;


    

    //服务端分页
    @RequestMapping("queryStudentList")
    public ResponseData queryStudentList(SearchUtil searchUtil){
        DataUtil<Student> dataUtil=studentService.queryStudentList(searchUtil);
        return ResponseData.success(dataUtil);
    }

    //uploadFile文件上传
    @RequestMapping("uploadFile")
    public ResponseData uploadFile(MultipartFile imgPath){
        try {
           String path = FileUtilesalbb.saveFile(imgPath.getInputStream(), imgPath.getOriginalFilename());
            return ResponseData.success(path);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseData.error(e);
        }
    }
    //新增学生信息
    @RequestMapping("addStudent")
    public ResponseData addStudent(Student student, HttpServletRequest request){
       student.setIsDel(1);
        //获取输入人的ip
        String ip = request.getRemoteAddr();
        student.setIp(ip);
        studentService.addStudent(student);
        return ResponseData.success(null);
    }

    //逻辑上删除
    @RequestMapping("updateStudentByIsDel")
    public  ResponseData updateStudentByIsDel(Integer id){
        try {
            studentService.updateStudentByIsDel(id);
            return ResponseData.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseData.error(e);
        }
    }

    //回显
    @RequestMapping("queryById")
    public ResponseData queryById(Integer id){
        Student student=studentService.queryById(id);
        return ResponseData.success(student);
    }
    //修改
    @RequestMapping("updateStudent")
    public ResponseData updateStudent(Student student,HttpServletRequest request){
        student.setIsDel(1);
        //获取输入人的ip
        String ip = request.getRemoteAddr();
        student.setIp(ip);
        studentService.updateStudent(student);
        return ResponseData.success(null);
    }
}
