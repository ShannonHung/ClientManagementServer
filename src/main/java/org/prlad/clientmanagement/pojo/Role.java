package org.prlad.clientmanagement.pojo;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role{
    ROOT, ADMIN, MEMBER, EMPLOYEE;
    public static final String ROLE_ROOT = "ROLE_ROOT";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MEMBER = "ROLE_MEMBER";

    private String name;
    static {
        ROOT.name = ROLE_ROOT;
        ADMIN.name = ROLE_ADMIN;
        MEMBER.name = ROLE_MEMBER;
    }


    Role() {
    }

    public String getName() {
        return name;
    }
}
