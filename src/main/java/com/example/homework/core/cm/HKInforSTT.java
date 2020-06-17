package com.example.homework.core.cm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author CcQun
 * @Date 2020/6/17 18:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HKInforSTT {
    private Integer to_evaluate_student_count;
    private Integer pass_student_count;
    private Integer submit_student_count;
    private Double average;
    private Integer max_point;
    private Integer min_point;
    private String max_point_owner;
}
