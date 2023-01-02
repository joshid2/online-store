package com.java.onlinestore.controllers;

import com.java.onlinestore.model.Item;
import com.java.onlinestore.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/item")
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping("/{name}")
    public List<Item> getAllItemsByName(@PathVariable("name") String name){
        return itemService.getAllItemsByName(name);
    }

    @GetMapping("/searchCatalog/{searchText}")
    public List<Item> searchItems(@PathVariable("searchText") String searchText){
        return itemService.searchItems(searchText);
    }

    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @PutMapping("/updateItem/{id}")
    public Item updateItem(@RequestBody Item item, @PathVariable int id){
        return itemService.updateItem(item, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> removeItem(@PathVariable("id") int id){
        Optional<Item> item = itemService.getItemById(id);

        if (item.isPresent()){
            itemService.removeItemById(id);
            return ResponseEntity.ok(item.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
