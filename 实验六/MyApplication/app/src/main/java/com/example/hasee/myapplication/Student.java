package com.example.hasee.myapplication;

import java.io.Serializable;

public class Student implements Serializable {

    public int id = -1;
    public String sno;
    public String sname;
    public String classes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "id：" + id + "  " + "学号：" + sno + "  " + "姓名：" + sname + "  " + "班级：" + classes;
    }
}
