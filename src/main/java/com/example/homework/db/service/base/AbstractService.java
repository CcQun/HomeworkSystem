package com.example.homework.db.service.base;

import com.example.homework.db.mapper.base.BaseMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/5/19 15:27
 */
public abstract class AbstractService<E, PK> {
    protected abstract BaseMapper<E, PK> getMapper();

    // 查询所有
    public List<E> findAll() {
        return getMapper().findAll();
    }

    // Example动态查询
    public List<E> findAll(E e) {
        Example<E> example = Example.of(e);
        List<E> list = getMapper().findAll(example);
        return list;
    }

    public List<E> findAll(Specification<E> specification) {
        List<E> list = getMapper().findAll(specification);
        return list;
    }


}
