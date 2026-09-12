package ru.yandex.share_it.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@Valid @RequestBody UserDto userDto) {
        log.info("Получен запрос на создание пользователя");
        return userService.save(userDto);
    }
    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") UUID id) {
        return userService.findById(id);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable UUID userId,
                          @Valid @RequestBody UserDto userDto) {
        return userService.update(userId,userDto);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        log.info("Получен запрос на удаление пользователя с id: {}", userId);
        userService.deleteById(userId);
        log.info("Пользователь с id: {} успешно удален", userId);
    }
}
