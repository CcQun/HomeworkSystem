package com.example.homework.db.service;

import com.example.homework.db.mapper.StudentMapper;
import com.example.homework.db.model.Student;
import com.example.homework.db.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * @Author CcQun
 * @Date 2020/6/15 16:22
 */
@Service
public class StudentService extends BaseService<Student, Integer, StudentMapper> {
}
