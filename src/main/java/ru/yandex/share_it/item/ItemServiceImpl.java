package ru.yandex.share_it.item;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.share_it.exception.EntityNotFound;
import ru.yandex.share_it.exception.ForbiddenException;
import ru.yandex.share_it.exception.GlobalExceptionHandler;
import ru.yandex.share_it.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;
    GlobalExceptionHandler globalExceptionHandler;

    public Item createItem(ItemDto itemDto,  UUID userId) {
        Item savedEntity = itemRepository.save(itemMapper.toEntity(itemDto, userId));
        log.info("Сохранена вещь: {}", savedEntity);
        return savedEntity;
    }

    public Item patchItem(ItemDto itemDto, UUID userId, UUID itemId) {
        Item item = itemRepository.findById(itemId).
                orElseThrow(()-> new EntityNotFound("Такой вещи не существует"));

        if (!item.getOwner().getId().equals(userId)) {
            throw new ForbiddenException("Редактировать вещь может только владелец");
        }

            itemMapper.updateEntity(itemDto, item);
            itemRepository.save(item);
            log.info("Сохранена измененная вещь: {}", item);


        return item;
    }

    public Item getItem(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(()->(new EntityNotFound("Такой вещи не существует")));
    }

    public List<Item> getMyItems(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(()->(new EntityNotFound("Такого пользователя не существует")));
        return itemRepository.findAllByOwnerId(userId);
    }

    public List<Item> getItemsByText(String text) {
        if(text == null ||text.isBlank()) {
            return new ArrayList<Item>();
        }
        return itemRepository.findAllByAvailableTrueAndNameContainingIgnoreCaseOrAvailableTrueAndDescriptionContainingIgnoreCase(text, text);
    }
}
