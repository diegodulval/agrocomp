package com.agrocomp.model.base.dao;

import com.agrocomp.model.base.BaseEntity;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseClienteDAO<E extends BaseEntity> {

    public void update(Connection conn, E entity) throws Exception;
    
   public List<E> readByCriteria(Connection conn,Map<Long, Object> criteria, Long limit, Long offset) throws Exception;

    public Long countByCriteria(Connection conn,Map<Long, Object> criteria) throws Exception;
    
    public String applyCriteria(Map<Long,Object> criteria);
   

    
}
