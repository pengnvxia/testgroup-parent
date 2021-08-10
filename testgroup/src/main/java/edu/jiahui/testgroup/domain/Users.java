package edu.jiahui.testgroup.domain;

import lombok.Data;

import java.util.Date;


@Data
public class Users {
    private Integer id;

    private String username;

    private String password;

    private String token;

    private String email;

    private Date createdAt;

    private String createdBy;

    private String deletedAt;

    private Date updatedAt;
}
