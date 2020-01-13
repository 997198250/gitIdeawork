package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.entity.po.Student;
import com.fh.entity.vo.SearchUtil;

import java.util.List;

public interface StudentDao extends BaseMapper<Student> {
    long queryCount(SearchUtil searchUtil);

    List<Student> queryStudentList(SearchUtil searchUtil);

    void updateStudentByIsDel(Integer id);
}
