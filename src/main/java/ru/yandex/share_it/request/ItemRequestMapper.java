package ru.yandex.share_it.request;

import ru.yandex.share_it.user.User;
import ru.yandex.share_it.user.UserRepository;

import java.util.UUID;

public class ItemRequestMapper {

    UserRepository userRepository;

    // TODO Заинжектить сервис, а не репозиторий

    public ItemRequest toEntity(
            ItemRequestDto dto,
            UUID requestorId
    ) {
        User requestor = userRepository.findById(requestorId)
                .orElseThrow(() -> new RuntimeException(
                        "Пользователь не найден: " + requestorId
                ));

        ItemRequest request = new ItemRequest();
        request.setDescription(dto.description());
        request.setRequestor(requestor);

        return request;
    }

    public ItemRequestDto toDto(ItemRequest itemRequest) {
        return new ItemRequestDto(
                itemRequest.getId(),
                itemRequest.getDescription(),
                itemRequest.getCreated()
        );
    }
}
