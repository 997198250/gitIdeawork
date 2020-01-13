package com.fh.service;

import com.fh.entity.po.Student;
import com.fh.entity.vo.DataUtil;
import com.fh.entity.vo.SearchUtil;

import java.util.List;

public interface StudentService {
    DataUtil<Student> queryStudentList(SearchUtil searchUtil);

    void addStudent(Student student);

    void updateStudentByIsDel(Integer id);

    Student queryById(Integer id);

    void updateStudent(Student student);

    List<Student> queryStudentAllList();
}
