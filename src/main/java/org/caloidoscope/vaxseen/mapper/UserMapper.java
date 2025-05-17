package org.caloidoscope.vaxseen.mapper;

import org.caloidoscope.vaxseen.dto.request.RegisterRequest;
import org.caloidoscope.vaxseen.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "linkedPatients", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toUser(RegisterRequest registerRequest);

    RegisterRequest toRegisterRequest(User user);
}
