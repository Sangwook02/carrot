package market.carrot.Service;

import lombok.RequiredArgsConstructor;
import market.carrot.DTO.ReadItemDTO;
import market.carrot.Domain.Item;
import market.carrot.Repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long create(Item item) {
        itemRepository.save(item);
        return item.getId();
    }
    @Transactional
    public void delete(Long id) {
        itemRepository.delete(itemRepository.findById(id));
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
    public Item findById(Long id) {
        return itemRepository.findById(id);
    }
    public ReadItemDTO findByIdV2(Long id) {
        return itemRepository.findItemDTO(id);
    }
    public List<Item> findByUser(Long id) {
        return itemRepository.findByUser(id);
    }
}
