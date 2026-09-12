package ru.yandex.share_it.booking;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingDto(UUID id,
                         LocalDateTime start,
                         LocalDateTime end,
                         UUID itemId,
                         UUID bookerId,
                         BookingStatus status) {
}
