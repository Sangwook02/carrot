package market.carrot.DTO;

import lombok.Data;
import market.carrot.Domain.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseItemDTO {
    private String name;
    private int price;
    private int liked;
    private List<String> images = new ArrayList<String>();
    private Category category;
    private LocalDateTime time;
    private boolean status;

    public ResponseItemDTO(ReadItemDTO readItemDTO) {
        this.name = readItemDTO.getName();
        this.price = readItemDTO.getPrice();
        this.liked = readItemDTO.getLiked();
        this.category = readItemDTO.getCategory();
        this.time = readItemDTO.getTime();
        this.status = readItemDTO.isStatus();
        if (readItemDTO.getPath1() != null) this.images.add(readItemDTO.getPath1());
        if (readItemDTO.getPath2() != null) this.images.add(readItemDTO.getPath2());
        if (readItemDTO.getPath3() != null) this.images.add(readItemDTO.getPath3());
        if (readItemDTO.getPath4() != null) this.images.add(readItemDTO.getPath4());
        if (readItemDTO.getPath5() != null) this.images.add(readItemDTO.getPath5());
        if (readItemDTO.getPath6() != null) this.images.add(readItemDTO.getPath6());
        if (readItemDTO.getPath7() != null) this.images.add(readItemDTO.getPath7());
        if (readItemDTO.getPath8() != null) this.images.add(readItemDTO.getPath8());
        if (readItemDTO.getPath9() != null) this.images.add(readItemDTO.getPath9());
        if (readItemDTO.getPath10() != null) this.images.add(readItemDTO.getPath10());
    }
}
