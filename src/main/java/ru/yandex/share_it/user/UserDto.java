package ru.yandex.share_it.user;

import java.util.UUID;

public record UserDto(UUID id,
                      String name,
                      String email) {
}
