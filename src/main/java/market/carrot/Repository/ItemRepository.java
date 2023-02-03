package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
import market.carrot.DTO.ReadItemDTO;
import market.carrot.Domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    public void delete(Item item) {
        em.remove(item);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public ReadItemDTO findItemDTO(Long id) {
        return em.createQuery("select new market.carrot.DTO.ReadItemDTO(i.name, i.price, i.liked, m.path1, m.path2, m.path3, m.path4, m.path5, m.path6, m.path7, m.path8,m.path9,m.path10, i.category, i.time, i.status)" +
                " from Item i" +
                " join i.itemImages m" +
                " where i.id = :id", ReadItemDTO.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public List<Item> findByUser(Long id) {
        return em.createQuery("select i from Item i join i.user u where u.id = :id")
                .setParameter("id",id)
                .getResultList();
    }
}
