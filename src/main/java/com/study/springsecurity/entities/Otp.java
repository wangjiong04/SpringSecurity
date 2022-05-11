package com.study.springsecurity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "opt")
@Entity
@SequenceGenerator(name = "opt_id_seq", sequenceName = "opt_id_seq", allocationSize = 1)
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opt_id_seq")
    private Integer id;

    private String  username;
    private String  code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
