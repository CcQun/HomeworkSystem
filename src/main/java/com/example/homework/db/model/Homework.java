package com.example.homework.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:19
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="Homework")
@DynamicInsert
@DynamicUpdate
public class Homework{
    @Id
    private Integer homework_number;
    private Integer teacher_number;
    private String homework_title;
    private String homework_content;
    private String homework_state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date create_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date update_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date end_time;
}
