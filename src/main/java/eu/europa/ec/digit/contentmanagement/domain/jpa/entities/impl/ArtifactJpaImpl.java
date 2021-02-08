package eu.europa.ec.digit.contentmanagement.domain.jpa.entities.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import eu.europa.ec.digit.contentmanagement.domain.api.entities.Artifact_i;
import eu.europa.ec.digit.contentmanagement.domain.api.entities.BaseType;
import eu.europa.ec.digit.contentmanagement.domain.jpa.entities.AbstractRepositoryObjectJpaImpl;

/**
 * 
 * @author bentsth
 */
@Entity(name = "Artifact")
@Table(name = "tbl_artifacts")
public class ArtifactJpaImpl extends AbstractRepositoryObjectJpaImpl implements Artifact_i {

    @Enumerated
    private BaseType baseType;

    @ManyToMany(targetEntity = ArtifactJpaImpl.class)
    @JoinTable(name = "tbl_artifacts_rel", 
        joinColumns = @JoinColumn(name = "child_id"), inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<Artifact_i> parents;


    public ArtifactJpaImpl() {
    }


    public ArtifactJpaImpl(List<Artifact_i> parents) {
        this.parents = parents;
    }


    public ArtifactJpaImpl(String name, List<Artifact_i> parents) {
        this.setName(name);
        this.parents = parents;
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
    public List<Artifact_i> getParents() {
        return parents;
    }


    @Override
    public void setParents(List<Artifact_i> parents) {
        this.parents = parents;
    }


    @Override
    public void addParent(Artifact_i parent) {
        if (parents == null) {
            synchronized (this) {
                if (parents == null) {
                    parents = new LinkedList<>();
                }
            }
        }

        parents.add(parent);
    }


    @Override
    public String toString() {
        return "ArtifactJpaImpl [getRepository()=" + getRepository() + ", getId()=" + getId() + ", getUuid()="
                + getUuid() + ", baseTypeDefinition=" + baseType + "]";
    }
}
