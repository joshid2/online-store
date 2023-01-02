package com.java.onlinestore.TestController;

import com.java.onlinestore.controllers.ItemController;
import com.java.onlinestore.model.Item;
import com.java.onlinestore.services.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest(classes = {TestItemController.class})
public class TestItemController {

    @Mock
    ItemService itemService;

    @InjectMocks
    ItemController itemController;

    List<Item> items;
    Item item;


    @Test
    void testGetAllItems(){

        items=new ArrayList<Item>();
        items.add(new Item(1,101,"Mobile",2,500.00));
        items.add(new Item(2,102,"Laptop",3,1000.00));

        when(itemService.getAllItems()).thenReturn(items);
        assertEquals(2,itemController.getAllItems().size());
        assertEquals("Laptop",itemController.getAllItems().get(1).getItemName());
    }

    @Test
    void testGetAllItemsByName(){
        items=new ArrayList<Item>();
        items.add(new Item(1,101,"Mobile",2,500.00));
        items.add(new Item(2,102,"Laptop",3,1000.00));
        when(itemService.getAllItemsByName("Mobile")).thenReturn(items);
        assertEquals(101,itemController.getAllItemsByName("Mobile").get(0).getSellerId());
        when(itemService.getAllItemsByName("Laptop")).thenReturn(items);
        assertEquals(1000.00,itemController.getAllItemsByName("Laptop").get(1).getPrice());
    }


    @Test
    void testSearchItem(){

        items=new ArrayList<Item>();
        items.add(new Item(1,101,"Mobile",2,500.00));
        items.add(new Item(2,102,"Laptop",3,1000.00));
        when(itemService.searchItems("Mobile")).thenReturn(items);
        assertEquals(500,itemController.searchItems("Mobile").get(0).getPrice());
    }

    @Test
    void testAddItem(){
        items=new ArrayList<Item>();
        item=new Item(1,101,"Mobile",2,500.00);

        when(itemService.addItem(item)).thenReturn(item);
        assertEquals(2,itemController.addItem(item).getQuantity());
    }

    @Test
    void testUpdateItem(){

        item=new Item(1,101,"Mobile",2,500.00);

        when(itemService.updateItem(item,1)).thenReturn(item);
        assertEquals("Mobile",itemController.updateItem(item,1).getItemName());

    }


    @Test
    void testRemoveItem() throws Exception {

        Optional<Item> item = Optional.of(new Item(2, 105, "Watch", 2, 100.0));
        when(itemService.getItemById(2)).thenReturn(item);

        ResponseEntity<Item> response=itemController.removeItem(2);
        // Verify the results
        assertEquals(HttpStatus.OK,response.getStatusCode());
       // assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getId());

        verify(itemService).removeItemById(2);
    }
}

