package org.caloidoscope.vaxseen.dto.request;

import org.caloidoscope.vaxseen.entity.Role;

public record RegisterRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String suffix,
        String phone,
        String address,
        Role role
){
}
