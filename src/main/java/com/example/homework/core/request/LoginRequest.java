package com.example.homework.core.request;

import lombok.Data;

/**
 * @Author CcQun
 * @Date 2020/6/15 15:10
 */
@Data
public class LoginRequest {
    private Integer id;
    private String password;
    private Integer ts;
}
