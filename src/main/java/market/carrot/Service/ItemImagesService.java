package market.carrot.Service;

import lombok.RequiredArgsConstructor;
import market.carrot.Domain.ItemImages;
import market.carrot.Domain.User;
import market.carrot.Repository.ItemImagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemImagesService {
    private final ItemImagesRepository itemImagesRepository;

    @Transactional
    public ItemImages create(ItemImages itemImages) {
        itemImagesRepository.save(itemImages);
        return itemImages;
    }

    public ItemImages findById(Long id) {
        return itemImagesRepository.findById(id);
    }
}
