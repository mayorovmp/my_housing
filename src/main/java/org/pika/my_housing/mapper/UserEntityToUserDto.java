package org.pika.my_housing.mapper;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.pika.my_housing.dto.user.UserDto;
import org.pika.my_housing.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDto {

    protected final ModelMapperConfig mapperConfig;

    @Autowired
    public UserEntityToUserDto(ModelMapperConfig mapperConfig) {
        this.mapperConfig = mapperConfig;
        configure();
    }

    private void configure() {
        var modelMapper = mapperConfig.modelMapper();
        modelMapper
                .createTypeMap(UserEntity.class, UserDto.class)
                .addMappings(
                        new PropertyMap<UserEntity, UserDto>() {
                            @Override
                            protected void configure() {
                                using((Converter<UserEntity, String>)
                                        context -> context.getSource() == null
                                                ? null : context.getSource().getLogin())
                                        .map(source, destination.getLogin());
                            }
                        });
    }
}
