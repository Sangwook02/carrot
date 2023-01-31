package market.carrot.Repository;

import lombok.RequiredArgsConstructor;
import market.carrot.Domain.Chat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRepository {
    private  final EntityManager em;

    public  void save(Chat chat) {
        if(chat.getId() == null) {
            em.persist(chat);
        }
        else {
            em.merge(chat);
        }
    }

    public void delete(Chat chat) {
        em.remove(chat);
    }

    public List<Chat> findByUser(Long id) {
        return em.createQuery("select c from Chat c join c.user u where u.id = :id")
                .setParameter("id",id)
                .getResultList();
    }
}
