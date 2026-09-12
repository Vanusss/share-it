package ru.yandex.share_it.item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    Item createItem(ItemDto itemDto, UUID userId);
    Item patchItem(ItemDto itemDto, UUID userId, UUID itemId);
    Item getItem(UUID itemId);
    List<Item> getMyItems(UUID userId);
    List<Item> getItemsByText(String text);

}
