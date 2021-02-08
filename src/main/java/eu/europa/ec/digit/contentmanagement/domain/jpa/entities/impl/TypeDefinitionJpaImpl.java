package eu.europa.ec.digit.contentmanagement.domain.jpa.entities.impl;

import javax.persistence.*;

import eu.europa.ec.digit.contentmanagement.domain.api.entities.*;
import eu.europa.ec.digit.contentmanagement.domain.jpa.entities.AbstractRepositoryObjectJpaImpl;

/**
 * 
 * @author bentsth
 */
@Entity(name = "TypeDefinition")
@Table(name = "tbl_typedefinitions")
public class TypeDefinitionJpaImpl extends AbstractRepositoryObjectJpaImpl implements TypeDefinition_i {

    @Basic
    private String name;
    @Basic
    private String displayName;
    @Basic
    private String description;
    @Basic
    private BaseType baseType;
    @ManyToOne(targetEntity = TypeDefinitionJpaImpl.class)
    private TypeDefinition_i parentTypeDefinition;
    @Basic
    private boolean creatable;
    @Basic
    private boolean fileable;
    @Basic
    private boolean queryable;
    @Basic
    private boolean fulltextIndexed;
    @Basic
    private boolean includedInSupertypeQuery;
    @Basic
    private boolean controllablePolicy;
    @Basic
    private boolean controllableAcl;

    @Basic
    private boolean canCreateSubType;
    @Basic
    private boolean canUpdateType;
    @Basic
    private boolean canDeleteType;


    public TypeDefinitionJpaImpl() {

    }


    /*
     * Used for test!
     */
    public TypeDefinitionJpaImpl(String name, String displayName, String description) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.baseType = BaseType.DOCUMENT;
        this.parentTypeDefinition = null;
        this.creatable = false;
        this.fileable = false;
        this.queryable = false;
        this.fulltextIndexed = false;
        this.includedInSupertypeQuery = false;
        this.controllablePolicy = false;
        this.controllableAcl = false;
        this.canCreateSubType = false;
        this.canUpdateType = false;
        this.canDeleteType = false;
    }


    public TypeDefinitionJpaImpl(String name, String displayName, String description, BaseType baseType,
            TypeDefinition_i parentTypeDefinition, boolean creatable, boolean fileable, boolean queryable, boolean fulltextIndexed,
            boolean includedInSupertypeQuery, boolean controllablePolicy, boolean controllableAcl,
            boolean canCreateSubType, boolean canUpdateType, boolean canDeleteType) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.baseType = baseType;
        this.parentTypeDefinition = parentTypeDefinition;
        this.creatable = creatable;
        this.fileable = fileable;
        this.queryable = queryable;
        this.fulltextIndexed = fulltextIndexed;
        this.includedInSupertypeQuery = includedInSupertypeQuery;
        this.controllablePolicy = controllablePolicy;
        this.controllableAcl = controllableAcl;
        this.canCreateSubType = canCreateSubType;
        this.canUpdateType = canUpdateType;
        this.canDeleteType = canDeleteType;
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
    public String getDisplayName() {
        return displayName;
    }


    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    public BaseType getBaseType() {
        return baseType;
    }


    @Override
    public void setBaseType(BaseType baseType) {
        this.baseType = baseType;
    }


    @Override
    public TypeDefinition_i getParentTypeDefinition() {
        return parentTypeDefinition;
    }


    @Override
    public void setParentTypeDefinition(TypeDefinition_i parentTypeDefinition) {
        this.parentTypeDefinition = parentTypeDefinition;
    }


    @Override
    public boolean isCreatable() {
        return creatable;
    }


    @Override
    public void setCreatable(boolean creatable) {
        this.creatable = creatable;
    }


    @Override
    public boolean isFileable() {
        return fileable;
    }


    @Override
    public void setFileable(boolean fileable) {
        this.fileable = fileable;
    }


    @Override
    public boolean isQueryable() {
        return queryable;
    }


    @Override
    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }


    @Override
    public boolean isFulltextIndexed() {
        return fulltextIndexed;
    }


    @Override
    public void setFulltextIndexed(boolean fulltextIndexed) {
        this.fulltextIndexed = fulltextIndexed;
    }


    @Override
    public boolean isIncludedInSupertypeQuery() {
        return includedInSupertypeQuery;
    }


    @Override
    public void setIncludedInSupertypeQuery(boolean includedInSupertypeQuery) {
        this.includedInSupertypeQuery = includedInSupertypeQuery;
    }


    @Override
    public boolean isControllablePolicy() {
        return controllablePolicy;
    }


    @Override
    public void setControllablePolicy(boolean controllablePolicy) {
        this.controllablePolicy = controllablePolicy;
    }


    @Override
    public boolean isControllableAcl() {
        return controllableAcl;
    }


    @Override
    public void setControllableAcl(boolean controllableAcl) {
        this.controllableAcl = controllableAcl;
    }


    @Override
    public boolean canCreateSubType() {
        return canCreateSubType;
    }


    @Override
    public void setCanCreateSubType(boolean canCreateSubType) {
        this.canCreateSubType = canCreateSubType;
    }


    @Override
    public boolean canUpdateType() {
        return canUpdateType;
    }


    @Override
    public void setCanUpdateType(boolean canUpdateType) {
        this.canUpdateType = canUpdateType;
    }


    @Override
    public boolean canDeleteType() {
        return canDeleteType;
    }


    @Override
    public void setCanDeleteType(boolean canDeleteType) {
        this.canDeleteType = canDeleteType;
    }


    @Override
    public String toString() {
        return "TypeDefinitionJpaImpl [getId()=" + getId() + ", getRepository()=" + getRepository() + ", getUuid()="
                + getUuid() + ", name=" + name + ", baseType=" + baseType + "]";
    }
    
    
    
}
