package eu.europa.ec.digit.contentmanagement.domain.jpa.entities;

import javax.persistence.*;

import eu.europa.ec.digit.contentmanagement.domain.api.entities.AbstractEntity_i;

/**
 * 
 * @author bentsth
 */
@MappedSuperclass
public class AbstractEntityJpaImpl implements AbstractEntity_i {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Basic
    private String uuid = "" + super.hashCode();

    @Basic
    private String name;


    public long getId() {
        return id;
    }


    @Override
    public String getUuid() {
        return uuid;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntityJpaImpl other = (AbstractEntityJpaImpl) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

}
