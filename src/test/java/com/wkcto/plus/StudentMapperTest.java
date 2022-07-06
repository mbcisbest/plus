package com.wkcto.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wkcto.order.entity.Student;
import com.wkcto.order.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

   //注入生成的StudentMapper
    @Autowired
    StudentMapper studentMapper;

    @Test
    public void testInsertStudent(){
        Student student  = new Student();
        student.setName("john");
        student.setAge(28);
        student.setEmail("john@yahu.com");
        student.setStatus(2);
        int rows  = studentMapper.insert(student);
        System.out.println("insert Student rows:"+rows);
    }

    @Test
    public void testSelect(){
        Student student = studentMapper.selectById(1);
        System.out.println("testSelect:"+student);
    }


    @Test
    public void testSelect1(){
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.gt("age",35);
        //selectOne：查询结果只能是一条记录或没有没有记录，多条记录是报错的
        Student student = studentMapper.selectOne(qw);
        System.out.println("testSelect:"+student);
    }
}


