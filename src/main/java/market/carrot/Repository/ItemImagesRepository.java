package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
import market.carrot.Domain.Item;
import market.carrot.Domain.ItemImages;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemImagesRepository {
    private final EntityManager em;

    public void save(ItemImages itemImages) {
        if (itemImages.getId() == null) {
            em.persist(itemImages);
        }
        else {
            em.merge(itemImages);
        }
    }

    public ItemImages findById(Long id) {
        return em.find(ItemImages.class, id);
    }
}
