package com.fh.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.common.Excel;
import com.fh.common.ExcelFild;
import com.fh.common.ExcleHeard;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName(value = "ssmp_student")
@ExcleHeard(title = "学生信息")
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField
    //@Excel(name="学生姓名",value = "name")
    @ExcelFild(name = "学生姓名")
    private String name;
    @TableField
    @Excel(name="学生年龄",value = "age")
    @ExcelFild(name = "学生年龄")
    private Integer age;
    @TableField
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="学生生日",value = "birthday")
    @ExcelFild(name = "学生生日")
    private Date  birthday;
    @TableField
    @ExcelFild(name = "家庭住址")
    private String address;
    @TableField
    private String  imgPath;
    @TableField
    private Integer isDel;
    @TableField
    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
