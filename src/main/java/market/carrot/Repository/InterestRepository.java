package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
import market.carrot.DTO.ItemListDTO;
import market.carrot.Domain.Interested;
import market.carrot.Domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterestRepository {
    private final EntityManager em;

    public void save(Interested interested) {
        if (interested.getId() == null) {
            em.persist(interested);
        }
        else {
            em.merge(interested);
        }
    }
    public List<ItemListDTO> findByUser(Long id) {
        return em.createQuery("select new market.carrot.DTO.ItemListDTO(t.itemImages.path1, t.name, t.price, t.liked, t.status)" +
                        " from Interested i "+
                        " join i.user u "+
                        " join i.item t" +
                        " where u.id = :id", ItemListDTO.class)
                .setParameter("id", id)
                .getResultList();
    }
}
