package com.dmdev.http.service;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.User;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.validator.CreateUserDtoValidator;
import com.dmdev.http.validator.ValidationResult;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    public static UserService getInstance() {
        return INSTANCE;
    }

    private UserService() {
    }

    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserDtoValidator createUserDtoValidator = CreateUserDtoValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto createUserDto) {
        // validation
        ValidationResult validationResult = createUserDtoValidator.isValid(createUserDto);
        if (validationResult.isNotValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        // mapper
        User userEntity = createUserMapper.map(createUserDto);

        // userDao.save
        userDao.save(userEntity);

        // return userDao.id
        Integer userEntityId = userEntity.getId();

        return userEntityId;
    }


}
