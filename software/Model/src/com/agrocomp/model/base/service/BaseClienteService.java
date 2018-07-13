package com.agrocomp.model.base.service;

import com.agrocomp.model.base.BaseCRUDService;
import com.agrocomp.model.entity.Cliente;

public interface BaseClienteService extends BaseCRUDService<Cliente> {

    public void updateUser(Cliente entity) throws Exception;
}
