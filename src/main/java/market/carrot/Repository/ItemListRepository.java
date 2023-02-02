package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
import market.carrot.DTO.ItemListDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemListRepository {
    private final EntityManager em;

    public List<ItemListDTO> findItembyUser(Long id) {
        return em.createQuery("select new market.carrot.DTO.ItemListDTO(g.path1, i.name, i.price, i.liked, i.status)" +
                " from Item i" +
                " join i.itemImages g" +
                " where i.user.id = : id", ItemListDTO.class)
                .setParameter("id", id)
                .getResultList();
    }
}
