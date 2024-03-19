package com.dmdev.http.mapper;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.entity.User;
import com.dmdev.http.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final String IMAGE_FOLDER = "users/";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    private CreateUserMapper() {
    }

    @Override
    public User map(CreateUserDto createUserDto) {
        return User.builder()
                .name(createUserDto.getName())
                .birthday(LocalDateFormatter.format(createUserDto.getBirthday()))
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .gender(Gender.valueOf(createUserDto.getGender()))
                .role(Role.valueOf(createUserDto.getRole()))
                .image(IMAGE_FOLDER + createUserDto.getImage().getSubmittedFileName())
                .build();


    }
}
