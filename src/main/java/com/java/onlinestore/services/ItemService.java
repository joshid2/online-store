package com.java.onlinestore.services;

import com.java.onlinestore.model.Item;
import com.java.onlinestore.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public List<Item> getAllItemsByName(String itemName){
        return itemRepository.findByItemName(itemName);
    }

    public List<Item> searchItems(String searchText){
        return itemRepository.findByItemNameContainingIgnoreCase(searchText);
    }
    public Optional<Item> getItemById(int id){
        return itemRepository.findById(id);
    }

    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public Item updateItem(Item updatedItem, int id){
        return itemRepository.findById(id)
                .map(item -> {
                    item.setSellerId(updatedItem.getSellerId());
                    item.setItemName(updatedItem.getItemName());
                    item.setPrice(updatedItem.getPrice());
                    item.setQuantity(updatedItem.getQuantity());
                    return itemRepository.save(item);
                })
                .orElseGet(() -> itemRepository.save(updatedItem));
    }

    public void removeItemById(int id){
        itemRepository.deleteById(id);
    }
}
