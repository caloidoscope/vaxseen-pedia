package org.caloidoscope.vaxseen.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Role {
    PARENT,
    HEALTHCARE_PROFESSIONAL_SECRETARY,
    HEALTHCARE_PROFESSIONAL,
    HEALTH_CENTER_ADMIN,
    LGU_ADMIN,
    SUPER_ADMIN;


    // Static map for role-based registration permissions
    private static final Map<Role, Set<Role>> registerPermissions = new HashMap<>();

    static {
        // Initialize the matrix of permissions
        registerPermissions.put(SUPER_ADMIN, Set.of(
                LGU_ADMIN,
                HEALTH_CENTER_ADMIN,
                HEALTHCARE_PROFESSIONAL,
                HEALTHCARE_PROFESSIONAL_SECRETARY,
                PARENT));
        registerPermissions.put(LGU_ADMIN, Set.of(
                HEALTH_CENTER_ADMIN,
                HEALTHCARE_PROFESSIONAL,
                PARENT));
        registerPermissions.put(HEALTH_CENTER_ADMIN, Set.of(
                HEALTHCARE_PROFESSIONAL,
                PARENT));
        registerPermissions.put(HEALTHCARE_PROFESSIONAL, Set.of(
                HEALTHCARE_PROFESSIONAL_SECRETARY,
                PARENT));
        registerPermissions.put(HEALTHCARE_PROFESSIONAL_SECRETARY, Set.of(
                PARENT));
    }

    // Method to check if a role can register a user with a specific new role
    public boolean canRegister(Role newRole) {
        return registerPermissions.getOrDefault(this, Set.of()).contains(newRole);
    }
}
