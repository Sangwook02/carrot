package market.carrot.Controller;

import lombok.Data;
import market.carrot.Domain.Category;
import market.carrot.Domain.Item;
import market.carrot.Domain.ItemImages;
import market.carrot.Domain.User;
import market.carrot.Service.ItemImagesService;
import market.carrot.Service.ItemService;
import market.carrot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemImagesService itemImagesService;

    /**
     * 상품 목록
     */
    @GetMapping("/items")
    public @ResponseBody List<Item> itemList() {
        return itemService.findAll();
    }

    /**
     * 상품 등록
     */
    @PostMapping(value = "items/new",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createItem(Principal principal, @RequestBody ItemDto itemDto) {
        Item i = new Item();
        i.setName(itemDto.getName());
        i.setPrice(itemDto.getPrice());
        ItemImages itemImages = new ItemImages();
        itemImages.setPath1(itemDto.getItemImages());
        i.setItemImages(itemImagesService.create(itemImages));
        i.setCategory(itemDto.getCategory());
        LocalDateTime now = LocalDateTime.now();
        i.setTime(now);
        i.setLiked(0);
        i.setStatus(true);
        User user = userService.findOne(principal.getName());
        i.setUser(user);
        itemService.create(i);
        return "new item made";
    }

    @Data
    static class ItemDto{
        private String name;
        private int price;
        private String itemImages;
        private Category category;
        private LocalDateTime time;
        private boolean status;
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping("/items/{item_id}")
    public @ResponseBody Item itemList(@PathVariable("item_id") Long id) {
        return itemService.findById(id);
    }

    /**
     * 특정 상품 삭제
     */
    @DeleteMapping("/items/{item_id}")
    public @ResponseBody String deleteItem(@PathVariable("item_id") Long id) {
        itemService.delete(id);
        return String.format("item %d deleted", id);
    }

    /**
     * 특정 상품 수정
     */
}
