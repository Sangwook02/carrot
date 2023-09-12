package market.carrot.DTO;

import lombok.Data;
import market.carrot.Controller.ItemController;
import market.carrot.Domain.Category;
import market.carrot.Domain.Item;
import market.carrot.Domain.ItemImages;

import java.time.LocalDateTime;

@Data
public class CreateItemDTO {
    private String name;
    private int price;
    private ItemImages itemImages;
    private Category category;
    private LocalDateTime time;

    public CreateItemDTO itemToDto(Item item) {
        CreateItemDTO itemDto = new CreateItemDTO();
        itemDto.setName(item.getName());
        itemDto.setItemImages(item.getItemImages());
        itemDto.setCategory(item.getCategory());
        itemDto.setTime(item.getTime());
        return itemDto;
    }
}
