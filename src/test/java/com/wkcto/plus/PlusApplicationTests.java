package com.wkcto.plus;

import com.wkcto.plus.entity.User;
import com.wkcto.plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlusApplicationTests {

    //使用自动注入， 注入Mapper对象(Dao)
    @Autowired
    private UserMapper userDao;

    //定义测试方法
    //测试添加操作， insert
    @Test
    public void testUserInsert(){
        //创建User对象


        for(int i=0;i<10;i++){
            User user  = new User();
            user.setName("zhangsan"+i);
            user.setAge(20 + i);
            user.setEmail("zhangsan@sina.com");
            //调用UserMapper的方法， 也就是父接口BaseMapper中的提供的方法
            int rows = userDao.insert(user);
            System.out.println("insert 的结果:"+rows);
        }


    }

    //添加数据后，获取主键值
    @Test
    public void testInsertGetId(){
        User user  = new User();
        user.setName("李四");
        user.setAge(20);
        user.setEmail("lisi@163.com");

        int rows  = userDao.insert(user);
        System.out.println("insert user rows:"+rows);

        //获取主键id ，刚添加数据库中的数据的id
        int id = user.getId();//主键字段对应的get方法
        System.out.println("主键id:"+id);

    }

    /**
     * 更新操作update
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setName("修改的数据");
        user.setAge(22);
        user.setEmail("edit@163.com");
        user.setId(2);
        //执行更新，根据主键值更新
        /*UPDATE user SET name=?, email=?, age=? WHERE id=?
         *更新了所有非null属性值， 条件where id = 主键值
         */
        int rows = userDao.updateById(user);
        System.out.println("update rows:"+rows);
    }

    /**
     * 控制更新的属性
     */
    @Test
    public void testUpdateUser2(){
        User user  = new User();
        user.setId(2);
        user.setName("zhangsan");
        //更新数据
        //UPDATE user SET name=? WHERE id=?
        int i = userDao.updateById(user);
        System.out.println("i:"+i);

    }

    /**
     * 更新数据： 实体类的属性是基本类型 - int age
     */
    @Test
    public void testUpdateUser3(){
        User user  = new User();
        user.setId(3);
        user.setEmail("lisi@sina.com");
        //实体对象 user: [name = null , email = "lisi@sina.com" , age = 0  ]
        //没有修改 name ,age
        //判断字段是否要修改， 加入到set语句， 是根据属性值是否为null .
        //UPDATE user SET email=?, age=? WHERE id=?
        int rows = userDao.updateById(user);
        System.out.println("rows："+rows);

    }


    /**
     * 按主键删除一条数据
     * 方法是deleteById()
     * 参数：主键值
     * 返回值：是删除的成功记录数
     */
    @Test
    public void testDeleteById(){

        //DELETE FROM user WHERE id=?
        int rows  = userDao.deleteById(3);
        System.out.println("deleteById:"+rows);
    }


    /**
     * 按条件删除数据， 条件是封装到Map对象中
     * 方法：deleteByMap(map对象);
     * 返回值：删除成功的记录数
     */
    @Test
    public void testDeleteByMap(){
        //创建Map对象，保存条件值
        Map<String,Object> map  = new HashMap<>();
        //put("表的字段名",条件值) ， 可以封装多个条件
        map.put("name","zs");
        map.put("age",20);
        //调用删除方法
        //DELETE FROM user WHERE name = ? AND age = ?
        int rows = userDao.deleteByMap(map);
        System.out.println("deleteByMap rows:"+rows);
    }


    /**
     * 批处理方式：使用多个主键值，删除数据
     * 方法名称：deleteBatchIds()
     * 参数： Collection<? extends Serializable> var1
     * 返回值：删除的记录数
     */
    @Test
    public void deleteByBatchId(){
        List<Integer> ids  = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);

        //使用lambda创建List集合
        List<Integer> idss = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        //删除操作
        //DELETE FROM user WHERE id IN ( ? , ? , ? , ? , ? )
        int i = userDao.deleteBatchIds(ids);
        System.out.println("deleteBatchIds:"+i);


    }



    /**
     * 实现查询 selectById ,根据主键值查询
     * 参数：主键值：
     * 返回值： 实体对象(唯一的一个对象)
     */
    @Test
    public void testSelectById(){
        /**
         * 生成的sql: SELECT id,name,email,age FROM user WHERE id=?
         * 如果根据主键没有查找到数据， 得到的返回值是 null
         */
        User user = userDao.selectById(6);
        System.out.println("selectById:"+user);

        //在使用对象之前，需要判断对象是否为null
        if(user != null){
            //业务方法的调用
        }


    }


    /**
     * 实现批处理查询，根据多个主键值查询， 获取到List
     * 方法：selectBatchIds
     * 参数：id的集合
     * 返回值：List<T>
     */
    @Test
    public void testSelectBatchId(){
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        ids.add(9);
        ids.add(10);

        //查询数据
        //SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? )
        List<User> users = userDao.selectBatchIds(ids);
        System.out.println("size:"+users.size());
        for (User u:users){
            System.out.println("查询的用户："+u);
        }
    }

    /**
     * 使用lambda查询数据
     */
    @Test
    public void testSelectBatchId2(){
        List<Integer> ids = Stream.of(6, 9, 10, 15).collect(Collectors.toList());
        ArrayList<Object> a1 = new ArrayList<>();
        a1.add(1);
        a1.add(2);
        a1.add(3);
        a1.add(4);


        //SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? , ? )
        List<User> users = userDao.selectBatchIds(ids);
        //遍历集合
        users.forEach( u -> {
            System.out.println("查询的user对象："+u);
        });
    }

    /**
     * 使用Map做多条件查询
     * 方法：selectByMap()
     * 参数：Map<String,Object>
     * 返回值：List<T>
     *
     */
    @Test
    public void testSelectMap(){
        //创建Map,封装查询条件
        Map<String,Object> map = new HashMap<>();
        //key是字段名， value：字段值 ，多个key，是and 联接
        map.put("name","zhangsan");
        map.put("age",20);

        //根据Map查询
        //SELECT id,name,email,age FROM user WHERE name = ? AND age = ?
        List<User> users = userDao.selectByMap(map);
        users.forEach(user -> {
            System.out.println("selectByMap:"+user);
        });

    }
}
