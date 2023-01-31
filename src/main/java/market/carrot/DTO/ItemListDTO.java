package market.carrot.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import market.carrot.Domain.Item;

@Data
public class ItemListDTO {
    private String image;
    private String name;
    private int price;
    private int liked;
    private boolean status;

    public ItemListDTO(Item item) {
        image = item.getItemImages().getPath1();
        name = item.getName();
        price = item.getPrice();
        liked = item.getLiked();
        status = item.isStatus();
    }
}
