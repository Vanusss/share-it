package ru.yandex.share_it.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        return User
                .builder()
                .name(dto.name())
                .email(dto.email())
                .build();
    }
    public void updateEntity(UserDto dto, User user) {
        if (dto.name() != null) {
            user.setName(dto.name());
        }

        if (dto.email() != null) {
            user.setEmail(dto.email());
        }
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
