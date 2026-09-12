package ru.yandex.share_it.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record ItemRequestDto(UUID id,
                             String description,
                             LocalDateTime created) {
}
