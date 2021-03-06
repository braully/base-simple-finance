//
// This file was generated by the JPA Modeler
//
package com.github.braully.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractEntity implements Serializable {

    @Basic
    private Long userIdModified;

    @Basic
    private Date lastModified;

    public AbstractAuditableEntity() {

    }

    public Long getUserIdModified() {
        return this.userIdModified;
    }

    public void setUserIdModified(Long userIdModified) {
        this.userIdModified = userIdModified;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
