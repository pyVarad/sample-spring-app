package com.app.aeportal.constant;

import com.google.common.collect.Sets;

import java.util.Set;
import static com.app.aeportal.constant.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, EMPLOYEE_READ, EMPLOYEE_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(ADMIN_READ, EMPLOYEE_READ));

    private final Set<ApplicationUserPermission> applicationUserPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> applicationUserPermissions) {
        this.applicationUserPermissions = applicationUserPermissions;
    }

    public Set<ApplicationUserPermission> getApplicationUserPermissions() {
        return applicationUserPermissions;
    }
}
