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
 * @Date 2020/6/15 12:18
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="Teacher")
public class Teacher {
    @Id
    private Integer teacher_number;
    private String teacher_name;
    private String teacher_password;
}
