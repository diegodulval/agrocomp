
package com.agrocomp.model.base.dao;

import com.agrocomp.model.base.BaseEntity;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BaseMensagemAnuncioDAO <E extends BaseEntity> {
    public void create(Connection conn,E entity) throws Exception;
    public List<E> readByCriteria(Connection conn,Map<Long, Object> criteria, Long limit, Long offset) throws Exception;
    public void delete(Connection conn,Long id) throws Exception;
    
}
