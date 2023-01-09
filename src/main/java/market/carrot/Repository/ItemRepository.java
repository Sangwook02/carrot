package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
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
    public List<Item> findByUser(Long id) {
        return em.createQuery("select i from Item i join i.user u where u.id = :id")
                .setParameter("id",id)
                .getResultList();
    }
}
