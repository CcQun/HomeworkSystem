package com.example.homework.db.service;

import com.example.homework.db.mapper.HomeworkMapper;
import com.example.homework.db.model.Homework;
import com.example.homework.db.model.pk.HomeworkPK;
import com.example.homework.db.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * @Author CcQun
 * @Date 2020/6/15 16:27
 */
@Service
public class HomeworkService extends BaseService<Homework, HomeworkPK, HomeworkMapper> {
}
