package com.java.onlinestore.TestService;


import com.java.onlinestore.model.Item;
import com.java.onlinestore.model.OrderDetails;
import com.java.onlinestore.repository.ItemRepository;
import com.java.onlinestore.services.ItemService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {TestItemService.class})
public class TestItemService {


    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    @Order(1)
    public void TestGetAllItems() {
        List<Item> item =new ArrayList<>();
        item.add(new Item(1,10,"Tab",2,200));
        item.add(new Item(2,20,"Watch",2,200));
        when(itemRepository.findAll()).thenReturn(item);
        assertEquals(2,itemService.getAllItems().size());
    }

    @Test
    @Order(2)
    public void TestGetAllItemByName() {

        List<Item> item =new ArrayList<>();
        item.add(new Item(1,10,"Tab",2,200));

        when(itemRepository.findByItemName("Tab")).thenReturn(item);
        assertEquals("Tab",itemService.getAllItemsByName("Tab").get(0).getItemName());

    }

    @Test
    @Order(3)
    public void TestGetItemById() {

        Item item = new Item(1, 101, "Mobile", 2, 500);
        when(itemRepository.findById(0)).thenReturn(Optional.of(item));
        Optional<Item> result = itemService.getItemById(0);
        assertTrue(result.isPresent());
        assertEquals("Mobile", result.get().getItemName());
    }


    @Test
    @Order(4)
    public void TestAddItem() {

        Item item = new Item(1, 101, "Mobile", 5, 500.00);

        when(itemRepository.save(item)).thenReturn(item);
        assertEquals("Mobile", itemService.addItem(item).getItemName());
    }


    @Test
    @Order(5)
    public void TestRemoveItemById(){

        itemService.removeItemById(0);
        verify(itemRepository).deleteById(0);

    }
}
