package com.agrocomp.model.base;

import java.util.List;
import java.util.Map;

//Para implementar a interface BaseCRUDService é necessario que a classe em questão extenda BaseEntity
//Deste modo só é possivél realizar operaçoes CRUD em Classes de Entidades
//<E> é a classe generica que extende BaseEntity
public interface BaseCRUDService<E extends BaseEntity> {
    
    public void create(E entity) throws Exception;

    public E readById(Long id) throws Exception;

    public List<E> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception;

    public Long countByCriteria(Map<Long, Object> criteria) throws Exception;

    public void update(E entity) throws Exception;

    public void delete(Long id) throws Exception;
    
    public Map<String,String> validate(Map<String,Object> filds) throws Exception;
}
