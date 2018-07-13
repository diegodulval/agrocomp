//Aqui definimos um padrao base para todas as classes de entidade do nosso model
//Todas as entidades terao um id sequencial
package com.agrocomp.model.base;

public abstract class BaseEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
