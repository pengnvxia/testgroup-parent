package edu.jiahui.testgroup.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;

    private Integer userId;

    private Byte role;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;
}
