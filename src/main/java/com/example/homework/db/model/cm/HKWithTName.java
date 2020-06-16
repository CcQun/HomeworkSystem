package com.example.homework.db.model.cm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author CcQun
 * @Date 2020/6/16 19:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HKWithTName {
    private Integer homework_number;
    private String teacher_name;
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
