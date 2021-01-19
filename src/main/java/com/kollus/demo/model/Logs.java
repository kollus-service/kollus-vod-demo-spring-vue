package com.kollus.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String type;
    @Column(length = 3000)
    String req;
    @Column(length = 3000)
    String res;
    @Column
    long log_at;

    public Logs() {
    }

    public Logs(long id, String type, String req, String res, long log_at) {
        this.id = id;
        this.type = type;
        this.req = req;
        this.res = res;
        this.log_at = log_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public long getLog_at() {
        return log_at;
    }

    public void setLog_at(long log_at) {
        this.log_at = log_at;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", req='" + req + '\'' +
                ", res='" + res + '\'' +
                ", log_at=" + log_at +
                '}';
    }
}
