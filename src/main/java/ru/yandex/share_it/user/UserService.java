package ru.yandex.share_it.user;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto save(UserDto userDto);
    List<UserDto> findAll();
    UserDto findById(UUID id);
    UserDto update(UUID userId, UserDto userDto);
    void deleteById(UUID id);
}
