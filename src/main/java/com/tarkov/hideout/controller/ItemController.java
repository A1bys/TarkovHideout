package com.tarkov.hideout.controller;

import com.tarkov.hideout.model.Item;
import com.tarkov.hideout.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController
{
    private final ItemService itemService;

    // Конструктор контроллера, внедряющий зависимость ItemService
    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }

    // Обработчик GET-запроса для получения всех предметов
    @GetMapping
    public List<Item> getAllItems()
    {
        return itemService.getAllItems();
    }

    // Обработчик GET-запроса для получения предмета по имени
    @GetMapping("/{name}")
    public Item getItemByName(@PathVariable String name)
    {
        return itemService.getItemByName(name);
    }

    // Обработчик POST-запроса для добавления нового предмета
    @PostMapping
    public Item addItem(@RequestBody Item item)
    {
        return itemService.addItem(item);
    }
}