package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * item 저장
     * */
    @Transactional
    public void saveItem(Item item) { itemRepository.save(item); }

    /**
     * item 정보 수정
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId); // id로 특정 상품을 찾음
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }

    /**
     * item 정보 조회
     * */
    // 전체 조회
    public List<Item> findItems() { return itemRepository.findAll(); }

    // 단건 조회
    public Item findOne(Long itemId) { return itemRepository.findOne(itemId); }

}