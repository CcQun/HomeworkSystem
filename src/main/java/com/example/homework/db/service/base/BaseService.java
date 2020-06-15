package com.example.homework.db.service.base;

import com.example.homework.db.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author CcQun
 * @Date 2020/5/19 15:36
 */
public abstract class BaseService<E, PK, M extends BaseMapper<E, PK>> extends AbstractService<E, PK> {
    @Autowired
    protected M mapper;

    @Override
    public BaseMapper<E, PK> getMapper() {
        return mapper;
    }
}
