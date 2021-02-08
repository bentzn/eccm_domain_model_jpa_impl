package eu.europa.ec.digit.contentmanagement.domain.jpa.entities.impl;

import javax.persistence.*;

import eu.europa.ec.digit.contentmanagement.domain.api.entities.Repository_i;
import eu.europa.ec.digit.contentmanagement.domain.jpa.entities.AbstractEntityJpaImpl;

/**
 * 
 * @author bentsth
 */
@Entity(name = "Repository")
@Table(name = "tbl_repositories")
public class RepositoryJpaImpl extends AbstractEntityJpaImpl implements Repository_i {

    @Basic
    private String name;

    @Basic
    private String description;

    @Basic
    private long rootFolderId;


    public RepositoryJpaImpl() {

    }


    public RepositoryJpaImpl(String name, String description, long rootFolderId) {
        this.name = name;
        this.description = description;
        this.rootFolderId = rootFolderId;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public long getRootFolderId() {
        return rootFolderId;
    }


    @Override
    public void setRootFolderId(long rootFolderId) {
        this.rootFolderId = rootFolderId;
    }


    @Override
    public String toString() {
        return "RepositoryJpaImpl [getId()=" + getId() + ", getUuid()=" + getUuid() + ", name=" + name
                + ", description=" + description + "]";
    }

}
