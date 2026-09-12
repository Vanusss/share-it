package ru.yandex.share_it.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@Valid @RequestBody ItemDto itemDto,
                           @RequestHeader("X-Sharer-User-Id") UUID userId) {
        log.info("Получен запрос на создание вещи");
        return itemService.createItem(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public Item patchItem(@RequestBody ItemDto itemDto,
                          @RequestHeader("X-Sharer-User-Id") UUID userId,
                          @PathVariable UUID itemId){
        log.info("Получен запрос на изменение вещи");
        return itemService.patchItem(itemDto, userId, itemId);
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable UUID itemId) {
        log.info("Получен запрос на предоставление информации про вещь №: {}", itemId);
        return itemService.getItem(itemId);
    }

    @GetMapping
    public List<Item> getMyItems(@RequestHeader("X-Sharer-User-Id") UUID userId) {
        log.info("Получен запрос на предоставление вещей пользователя №: {}", userId);
        return itemService.getMyItems(userId);
    }

    @GetMapping("/search")
    public List<Item> getItemsByText(@RequestParam String text) {
        return itemService.getItemsByText(text);
    }
}
