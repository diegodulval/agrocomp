package com.agrocomp.model.base.service;

import com.agrocomp.model.base.BaseEntity;
import java.util.List;
import java.util.Map;

public interface BaseReporteService<E extends BaseEntity> {

    public void create(E entity) throws Exception;

    public List<E> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception;
    
    public void delete(Long id) throws Exception;

    public Map<Long, String> validate(E entity);

}
