package com.tarkov.hideout.repository;

import com.tarkov.hideout.model.Item;
import com.tarkov.hideout.repository.ItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>
{
    Item findByName(String name); // Метод для поиска предмета по имени
}