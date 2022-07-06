package com.wkcto.plus;

import com.wkcto.plus.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptARTest {

    @Test
    public void testARInsert(){
        //定义dept的实体

        Dept dept  = new Dept();
        dept.setName("行政部");
        dept.setMobile("010-66666666");
        dept.setManager(5);
        //调用实体对象自己的方法，完成对象自身到数据库的添加操作
        boolean flag = dept.insert();
        System.out.println("ar insert result:"+flag);
    }


    @Test
    public void testARUpdate(){
        //定义实体Dept
        Dept dept  = new Dept();
       // dept.setId(2);
        dept.setMobile("010-22222222");
        dept.setName("改为市场部");
        dept.setManager(2);
        //根据主键id更新记录
        // UPDATE dept SET name=?, mobile=?, manager=? WHERE id=?  // id = 1
        boolean result = dept.updateById();//使用dept实体主键的值，作为where id = 1
        System.out.println("ar updateById result:"+result);
    }


    @Test
    public void testARUpdate2(){
        //定义实体Dept
        Dept dept  = new Dept();
       // dept.setId(1);
        dept.setMobile("010-3333333");
        //name , manager是没有修改的

        //根据主键id更新记录
        // UPDATE dept SET name=?, mobile=?, manager=? WHERE id=?  // id = 1
        // null的属性值不做更新处理，在update中没有null的字段
        //UPDATE dept SET mobile=? WHERE id=?
        boolean result = dept.updateById();//使用dept实体主键的值，作为where id = 1
        System.out.println("ar updateById result:"+result);
    }

    /**
     * deleteById()删除操作即使没有从数据库中删除数据，也返回是true
     */
    @Test
    public void testARDeleteById(){
        Dept dept  = new Dept();
        //DELETE FROM dept WHERE id=?
        boolean result = dept.deleteById(1);
        System.out.println("ar deleteById result:"+result);
    }

    @Test
    public void testARDeleteById2(){
        Dept dept  = new Dept();
        //dept.setId(2);
        //DELETE FROM dept WHERE id=?
        boolean result = dept.deleteById();
        System.out.println("ar deleteById result:"+result);
    }

    /**
     * selectByID
     * 1.按实体的主键能查找出数据，返回对象
     * 2.按实体的主键不能查出数据，是null ，不报错。
     */
   @Test
    public void testARSelectById(){
       Dept dept = new Dept();
        //设置主键的值
        //dept.setId(1);
        //调用查询方法
        //SELECT id,name,mobile,manager FROM dept WHERE id=?
       Dept dept1 = dept.selectById();
       System.out.println("ar selectById result:"+dept1);
   }

    /**
     * selectById(主键)
     * 1.主键有记录，返回实体对象
     * 2.主键没有记录，返回是null
     *
     */
    @Test
    public void testARSelectById2(){
        Dept dept = new Dept();
        Dept dept1 = dept.selectById(3);
        System.out.println("dept1:"+dept1);


    }
}
