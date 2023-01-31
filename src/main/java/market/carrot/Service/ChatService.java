package market.carrot.Service;

import lombok.RequiredArgsConstructor;
import market.carrot.Domain.Chat;
import market.carrot.Repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @Transactional
    public Long create(Chat chat) {
        chatRepository.save(chat);
        return chat.getId();
    }

    @Transactional
    public void delete(Chat chat) {
        chatRepository.delete(chat);
    }

    public List<Chat> findByUser(Long id) {
        return chatRepository.findByUser(id);
    }


}
