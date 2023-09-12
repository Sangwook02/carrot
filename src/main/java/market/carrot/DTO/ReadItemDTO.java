package market.carrot.DTO;

import lombok.Data;
import market.carrot.Domain.Category;
import market.carrot.Domain.ItemImages;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReadItemDTO {
    private String name;
    private int price;
    private int liked;
    private String path1;
    private String path2;
    private String path3;
    private String path4;
    private String path5;
    private String path6;
    private String path7;
    private String path8;
    private String path9;
    private String path10;

    private Category category;
    private LocalDateTime time;
    private boolean status;

    public ReadItemDTO(String name, int price, int liked, String path1, String path2, String path3, String path4, String path5, String path6, String path7, String path8, String path9, String path10, Category category, LocalDateTime time, boolean status) {
        this.name = name;
        this.price = price;
        this.liked = liked;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
        this.path4 = path4;
        this.path5 = path5;
        this.path6 = path6;
        this.path7 = path7;
        this.path8 = path8;
        this.path9 = path9;
        this.path10 = path10;
        this.category = category;
        this.time = time;
        this.status = status;
    }
}
