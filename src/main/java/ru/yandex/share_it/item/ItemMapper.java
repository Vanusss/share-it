package ru.yandex.share_it.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.share_it.exception.EntityNotFound;
import ru.yandex.share_it.exception.GlobalExceptionHandler;
import ru.yandex.share_it.request.ItemRequest;
import ru.yandex.share_it.request.ItemRequestRepository;
import ru.yandex.share_it.user.User;
import ru.yandex.share_it.user.UserRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final UserRepository userRepository;
    private final ItemRequestRepository itemRequestRepository;
    GlobalExceptionHandler  globalExceptionHandler;

    public Item toEntity(ItemDto dto, UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFound(
                        "Пользователь не найден: " + ownerId
                ));

        ItemRequest request = null;

        if (dto.requestId() != null) {
            request = itemRequestRepository.findById(dto.requestId())
                    .orElseThrow(() -> new EntityNotFound(
                            "Запрос не найден: " + dto.requestId()
                    ));
        }

        Item item = new Item();
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setAvailable(dto.available());
        item.setOwner(owner);
        item.setRequest(request);

        return item;
    }

    public void updateEntity(ItemDto dto, Item item) {
        if (dto.name() != null) {
            item.setName(dto.name());
        }

        if (dto.description() != null) {
            item.setDescription(dto.description());
        }

        if (dto.available() != null) {
            item.setAvailable(dto.available());
        }
    }

    public ItemDto toDto(Item item) {
        UUID requestId = item.getRequest() == null
                ? null
                : item.getRequest().getId();

        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.isAvailable(),
                requestId
        );
    }
}