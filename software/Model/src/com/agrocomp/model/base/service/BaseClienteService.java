/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agrocomp.model.base.service;

import com.agrocomp.model.base.BaseCRUDService;
import com.agrocomp.model.entity.Cliente;

/**
 *
 * @author couto
 */
public interface BaseClienteService extends BaseCRUDService<Cliente>{
    public void updateUser(Cliente entity)throws Exception;
}
