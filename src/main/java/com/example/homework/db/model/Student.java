package com.example.homework.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:14
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="Student")
public class Student {
    @Id
    private Integer student_number;
    private String student_name;
    private String student_password;
}
