package com.fh.service.impl;

import com.fh.dao.StudentDao;
import com.fh.entity.po.Student;
import com.fh.entity.vo.DataUtil;
import com.fh.entity.vo.SearchUtil;
import com.fh.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public DataUtil<Student> queryStudentList(SearchUtil searchUtil) {
        long count=studentDao.queryCount(searchUtil);
        List<Student> studentList=studentDao.queryStudentList(searchUtil);
        DataUtil<Student> dataUtil=new DataUtil<>();
        dataUtil.setDraw(searchUtil.getDraw());
        dataUtil.setRecordsTotal((int)count);
        dataUtil.setRecordsFiltered((int)count);
        dataUtil.setData(studentList);
        return dataUtil;
    }

    @Override
    public void addStudent(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void updateStudentByIsDel(Integer id) {
        studentDao.updateStudentByIsDel(id);
    }

    @Override
    public Student queryById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateById(student);
    }
}
