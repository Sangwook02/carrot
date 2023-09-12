package market.carrot.DTO;


import lombok.Data;
import market.carrot.Domain.Item;

@Data
public class ItemListDTO {
    private String image;
    private String name;
    private int price;
    private int liked;
    private boolean status;

    public ItemListDTO(String image, String name, int price, int liked, boolean status) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.liked = liked;
        this.status = status;
    }

    public ItemListDTO(Item item) {
        image = item.getItemImages().getPath1();
        name = item.getName();
        price = item.getPrice();
        liked = item.getLiked();
        status = item.isStatus();
    }
}
