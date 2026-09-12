package ru.yandex.share_it.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.share_it.exception.EntityNotFound;
import ru.yandex.share_it.exception.GlobalExceptionHandler;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    GlobalExceptionHandler  globalExceptionHandler;

    @Override
    public UserDto save(UserDto userDto) {
        User savedUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(savedUser);

    }

    @Override
    public List<UserDto> findAll() {
        List<User> users =  userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto findById(UUID id){
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("такого пользователя не существует")));
    }

    @Override
    public UserDto update(UUID userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFound("такого пользователя не существует"));
        if (userDto.email() != null && !userDto.email().isBlank()) {
            user.setEmail(userDto.email());
        }

        if (userDto.name() != null && !userDto.name().isBlank()) {
            user.setName(userDto.name());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
