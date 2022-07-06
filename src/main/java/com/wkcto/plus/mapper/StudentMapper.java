package com.wkcto.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wkcto.plus.entity.Student;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    //自定义方法
    public int insertStudent(Student student);
    public Student selectStudentById(Integer id);
    public List<Student> selectByName(String name);
}
