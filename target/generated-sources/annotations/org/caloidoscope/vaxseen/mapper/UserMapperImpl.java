package org.caloidoscope.vaxseen.mapper;

import javax.annotation.processing.Generated;
import org.caloidoscope.vaxseen.dto.request.RegisterRequest;
import org.caloidoscope.vaxseen.entity.Role;
import org.caloidoscope.vaxseen.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T00:55:59+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( registerRequest.username() );
        user.setPassword( registerRequest.password() );
        user.setEmail( registerRequest.email() );
        user.setFirstName( registerRequest.firstName() );
        user.setLastName( registerRequest.lastName() );
        user.setSuffix( registerRequest.suffix() );
        user.setPhone( registerRequest.phone() );
        user.setAddress( registerRequest.address() );
        user.setRole( registerRequest.role() );

        return user;
    }

    @Override
    public RegisterRequest toRegisterRequest(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        String password = null;
        String email = null;
        String firstName = null;
        String lastName = null;
        String suffix = null;
        String phone = null;
        String address = null;
        Role role = null;

        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        suffix = user.getSuffix();
        phone = user.getPhone();
        address = user.getAddress();
        role = user.getRole();

        RegisterRequest registerRequest = new RegisterRequest( username, password, email, firstName, lastName, suffix, phone, address, role );

        return registerRequest;
    }
}
