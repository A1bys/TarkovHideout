package com.tarkov.hideout.service;

import com.tarkov.hideout.repository.ItemRepository;
import com.tarkov.hideout.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService
{
    private final ItemRepository itemRepository;

    // Конструктор сервиса, внедряющий зависимость ItemRepository
    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    // Метод для получения всех предметов
    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }

    // Метод для получения предмета по имени
    public Item getItemByName(String name)
    {
        return itemRepository.findByName(name);
    }

    // Метод для добавления нового предмета
    public Item addItem(Item item)
    {
        return itemRepository.save(item);
    }
}