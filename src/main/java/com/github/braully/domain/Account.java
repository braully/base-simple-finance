//
// This file was generated by the JPA Modeler
//
package com.github.braully.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Account extends AbstractMigrableEntity implements Serializable {

    @Basic
    private String typeGL;

    @Basic
    private String groupingGL;

    @Basic
    private String name;

    @Basic
    private String description;

    @ManyToOne(targetEntity = Account.class)
    private Account parentAccount;

    public Account() {

    }

    public String getTypeGL() {
        return this.typeGL;
    }

    public void setTypeGL(String typeGL) {
        this.typeGL = typeGL;
    }

    public String getGroupingGL() {
        return this.groupingGL;
    }

    public void setGroupingGL(String groupingGL) {
        this.groupingGL = groupingGL;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getParentAccount() {
        return this.parentAccount;
    }

    public void setParentAccount(Account parentAccount) {
        this.parentAccount = parentAccount;
    }
}
