package com.portfolio.ecsite.service.item;

import com.portfolio.ecsite.repository.item.ItemRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@DisplayName("商品編集、購入画面のテスト")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemEntityNotFoundExceptionTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    @Order(1)
    @DisplayName("存在しない商品の検索テスト")
    void testFindNonExistingItem() {
        // モックの準備
        Long itemId = 2L;
        when(itemRepository.select(itemId)).thenReturn(Optional.empty());

        // テスト＆検証
        ItemEntityNotFoundException thrown = assertThrows(ItemEntityNotFoundException.class, () -> {
            itemService.find(itemId);
        });
        assertEquals(itemId, thrown.getItemId());
    }

}