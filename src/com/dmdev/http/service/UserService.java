package com.dmdev.http.service;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.dto.UserDto;
import com.dmdev.http.entity.User;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.mapper.UserMapper;
import com.dmdev.http.validator.CreateUserDtoValidator;
import com.dmdev.http.validator.ValidationResult;
import lombok.SneakyThrows;

import java.util.Optional;

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
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    @SneakyThrows
    public Integer create(CreateUserDto createUserDto) {
        // validation
        ValidationResult validationResult = createUserDtoValidator.isValid(createUserDto);
        if (validationResult.isNotValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        // mapper
        User userEntity = createUserMapper.map(createUserDto);

        // Сохранение картинки + userDao.save (по идее это надо делать в транзакции, но тут для простоты не испозуется)
        imageService.upload(userEntity.getImage(), createUserDto.getImage().getInputStream());
        userDao.save(userEntity);

        // return userDao.id
        return userEntity.getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::map);
    }

}
