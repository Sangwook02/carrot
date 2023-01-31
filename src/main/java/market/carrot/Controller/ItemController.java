package market.carrot.Controller;

import lombok.Data;
import market.carrot.DTO.ItemListDTO;
import market.carrot.Domain.*;
import market.carrot.Service.InterestService;
import market.carrot.Service.ItemImagesService;
import market.carrot.Service.ItemService;
import market.carrot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private InterestService interestService;
    @Autowired
    private ItemImagesService itemImagesService;

    /**
     * 상품 목록
     */
    @GetMapping("/v1/items")
    public @ResponseBody List<Item> itemListV1() {
        return itemService.findAll();
    }
    @GetMapping("/v2/items")
    public @ResponseBody List<ItemListDTO> itemListV2() {
        List<Item> items = itemService.findAll();
        return items.stream()
                .map(item -> new ItemListDTO(item))
                .collect(toList());
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
        itemImages.setPath1(itemDto.getItemImages().getPath1());
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
        private ItemImages itemImages;
        private Category category;
        private LocalDateTime time;

        public ItemDto itemToDto(Item item) {
            ItemDto itemDto = new ItemDto();
            itemDto.setName(item.getName());
            itemDto.setItemImages(item.getItemImages());
            itemDto.setCategory(item.getCategory());
            itemDto.setTime(item.getTime());
            return itemDto;
        }
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
    public @ResponseBody String deleteItem(Principal principal, @PathVariable("item_id") Long id) {
        Item item = itemService.findById(id);
        if (principal.getName() == item.getUser().getEmail()) {
            // 상품 등록자인 경우에만 가능하도록 수정할 것.
            itemService.delete(id);
            return String.format("item %d deleted", id);
        }

        return "You do not have permission to delete this item";
    }

    /**
     * 특정 상품 수정
     */
    @PatchMapping("/items/{item_id}")
    public @ResponseBody Item updateItem(Principal principal ,@PathVariable ("item_id") Long id, @RequestBody ItemDto itemDto) {
        Item item = itemService.findById(id);
        if (userService.findOne(principal.getName()).getEmail() == item.getUser().getEmail()) {
            ItemDto itemDto1 = new ItemDto();
            if(itemDto1.itemToDto(item) != itemDto) {
                item.setTime(LocalDateTime.now());
                if(itemDto.getName() != null) {
                    item.setName(itemDto.getName());
                }
                // 기존의 row에 수정된 path만 삽입
                ItemImages itemImages = item.getItemImages();
                if (itemDto.getItemImages() != null) {
                    if (itemDto.getItemImages().getPath1() != null) {
                        itemImages.setPath1(itemDto.getItemImages().getPath1());
                    }
                    if (itemDto.getItemImages().getPath2() != null) {
                        itemImages.setPath2(itemDto.getItemImages().getPath2());
                    }
                    if (itemDto.getItemImages().getPath3() != null) {
                        itemImages.setPath3(itemDto.getItemImages().getPath3());
                    }
                    if (itemDto.getItemImages().getPath4() != null) {
                        itemImages.setPath4(itemDto.getItemImages().getPath4());
                    }
                    if (itemDto.getItemImages().getPath5() != null) {
                        itemImages.setPath5(itemDto.getItemImages().getPath5());
                    }
                    if (itemDto.getItemImages().getPath6() != null) {
                        itemImages.setPath6(itemDto.getItemImages().getPath6());
                    }
                    if (itemDto.getItemImages().getPath7() != null) {
                        itemImages.setPath7(itemDto.getItemImages().getPath7());
                    }
                    if (itemDto.getItemImages().getPath8() != null) {
                        itemImages.setPath8(itemDto.getItemImages().getPath8());
                    }
                    if (itemDto.getItemImages().getPath9() != null) {
                        itemImages.setPath9(itemDto.getItemImages().getPath9());
                    }
                    if (itemDto.getItemImages().getPath10() != null) {
                        itemImages.setPath10(itemDto.getItemImages().getPath10());
                    }
                    itemImagesService.create(itemImages);
                }
                if (itemDto.getCategory() != null) {
                    item.setCategory(itemDto.getCategory());
                }
                if (itemDto.getPrice() != 0) {
                    item.setPrice(itemDto.getPrice());
                }
            }
        }
        else {
            return item;
        }

        itemService.create(item);
        return item;
    }
    /**
     * 관심 상품 등록
     */
    @PostMapping("/items/{item_id}")
    public @ResponseBody String likeItem(Principal principal ,@PathVariable ("item_id") Long id) {
        Interested interested = new Interested();
        User user = userService.findOne(principal.getName());
        interested.setUser(user);
        Item item = itemService.findById(id);
        interested.setItem(item);
        interestService.save(interested);
        item.setLiked(item.getLiked() + 1);
        return "added";
    }
}