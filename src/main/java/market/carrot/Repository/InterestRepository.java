package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
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
    public List<Item> findByUser(Long id) {
        return em.createQuery("select i from Interested i join i.user u where u.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
