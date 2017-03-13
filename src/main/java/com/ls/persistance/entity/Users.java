package com.ls.persistance.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lushuang
 * Date: 2017/03/13
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}