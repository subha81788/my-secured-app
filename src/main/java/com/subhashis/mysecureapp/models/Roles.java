package com.subhashis.mysecureapp.models;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Roles implements Serializable {
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoleSet() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles.addAll(roles.getRoleSet());
    }

    public void setRoles(Set<Role> roles) {
        this.roles.addAll(roles);
    }
}
