package com.dmdev.http.validator;


import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Gender;
import com.dmdev.http.entity.Role;
import com.dmdev.http.util.EnumUtils;
import com.dmdev.http.util.LocalDateFormatter;

import java.util.Optional;

public class CreateUserDtoValidator implements Validator<CreateUserDto> {

    private static final CreateUserDtoValidator INSTANCE = new CreateUserDtoValidator();

    public static CreateUserDtoValidator getInstance() {
        return INSTANCE;
    }

    private CreateUserDtoValidator() {
    }

    @Override
    public ValidationResult isValid(CreateUserDto createUserDto) {
        ValidationResult validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(createUserDto.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }

        Optional<Gender> gender = EnumUtils.find(createUserDto.getGender(), Gender.class);
        if (gender.isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }

        Optional<Role> role = EnumUtils.find(createUserDto.getRole(), Role.class);
        if (role.isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }

        return validationResult;
    }
}
