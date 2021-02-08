package eu.europa.ec.digit.contentmanagement.domain.jpa.entities;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import eu.europa.ec.digit.contentmanagement.domain.api.entities.AbstractRepositoryObject_i;
import eu.europa.ec.digit.contentmanagement.domain.api.entities.Repository_i;
import eu.europa.ec.digit.contentmanagement.domain.jpa.entities.impl.RepositoryJpaImpl;

/**
 * 
 * @author bentsth
 */
@MappedSuperclass
public class AbstractRepositoryObjectJpaImpl extends AbstractEntityJpaImpl implements AbstractRepositoryObject_i {

    @ManyToOne(targetEntity = RepositoryJpaImpl.class)
    private Repository_i repository;


    @Override
    public Repository_i getRepository() {
        return repository;
    }


    @Override
    public void setRepository(Repository_i repository) {
        this.repository = repository;
    }

}
