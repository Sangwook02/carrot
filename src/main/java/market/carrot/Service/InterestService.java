package market.carrot.Service;

import lombok.RequiredArgsConstructor;
import market.carrot.Domain.Interested;
import market.carrot.Domain.Item;
import market.carrot.Repository.InterestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    @Transactional
    public Long save(Interested interested) {
        interestRepository.save(interested);
        return interested.getId();
    }
    public List<Item> findByUser(Long id) {
        return interestRepository.findByUser(id);
    }
}
