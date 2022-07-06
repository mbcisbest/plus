package com.wkcto.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @TableName(value="表名")
 * 位置：在类定义的上面
 */
@TableName(
        value = "user_address"
)
public class Address {

    //指定主键
    @TableId(value="user_id",type  = IdType.AUTO)
    private Integer id;
    /**
     * @TableField : 指定属性和列名的对应关系。
     *    属性： value 指定列名
     */
    @TableField(value = "user_city")
    private String city;
    @TableField(value = "user_street")
    private String street;

    private String zipcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
