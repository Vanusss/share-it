package ru.yandex.share_it.item;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ItemDto(UUID id,
                      @NotBlank(message = "Название не может быть пустым")
                      String name,
                      @NotBlank(message = "Описание не может быть пустым")
                      String description,
                      Boolean available,
                      UUID requestId) {
}
