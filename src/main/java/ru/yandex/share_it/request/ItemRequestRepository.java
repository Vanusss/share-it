package ru.yandex.share_it.request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, UUID> {
}