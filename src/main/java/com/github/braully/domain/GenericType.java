//
// This file was generated by the JPA Modeler
//
package com.github.braully.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class GenericType extends AbstractEntity implements Serializable {

    @Basic
    private String attribute;

    @Basic
    private String type;

    @Basic
    private String grouping;

    public GenericType() {

    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrouping() {
        return this.grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }
}
