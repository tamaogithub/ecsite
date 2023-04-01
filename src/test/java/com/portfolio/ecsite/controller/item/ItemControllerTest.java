package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.service.item.ItemService;
import com.portfolio.ecsite.service.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// テスト対象のサーバを起動して、Controllerのテストを行えるようにするアノテーション
@SpringBootTest
@AutoConfigureMockMvc
// @SpringBootTestアノテーションの代わりに、@WebMvcTestアノテーションを利用
//@Importアノテーションで、テスト対象クラスで参照するクラスをDIできるように指定
//@WebMvcTest(controllers = ItemController.class)
//@Import({RestTemplate.class, HttpHeaders.class})
class ItemControllerTest {

    @Autowired
    ItemController itemController;

    // ItemServiceにMockオブジェクトをDIする
    @MockBean
    private ItemService itemService;

    @MockBean
    private UserService userService;

    MockMvc mockMvc;

//    @Autowired
//    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }


    @Nested @Order(1) @DisplayName("UserControllerの検証")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ControllerTest {

        @Test
        @Order(1)
        @DisplayName("商品一覧画面のアクセス")
        void testShowList() throws Exception {
            MvcResult result = mockMvc.perform(get("/items?limit=10&offset=0").accept(MediaType.TEXT_HTML))
            .andExpect(model().attributeExists("itemList","total","page","totalPage", "startPage", "endPage","offset","preOffset","itemList","base64Data"))
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("items/list")).andReturn();
        }

        @Test
        @Order(2)
        @DisplayName("商品登録画面のアクセス")
        void showCreationForm() throws Exception {
            MvcResult result = mockMvc.perform(get("/items/creationForm").accept(MediaType.TEXT_HTML))
            .andExpect(model().attributeExists("base64Data"))
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("items/creationForm")).andReturn();
        }
        @Test
        @Order(3)
        @DisplayName("商品登録画面の「登録」ボタンを押下後、商品一覧画面にリダイレクト")
        void createItemTest() throws Exception {
//            var itemFroms = new ItemForms("歯ブラシ","歯ブラシ（極細）",null, null ,"LION",198,3,null);
//            var objectMapper = new ObjectMapper();
//            mockMvc.perform(
//                post("/items/creationForm")
//                        .content(objectMapper.writeValueAsString(itemFroms))  // リクエストボディを指定
//                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Content Typeを指定
//                ).andExpect(status().isCreated());
//                    .andExpect(status().isOk())
//                    .andExpect(model().hasNoErrors())
//                    .andExpect(view().name("items/creationForm")).andReturn();
        }

        @Test
        @Order(4)
        @DisplayName("商品詳細画面のアクセス")
        void showDiscriptionFrom() throws Exception {
            mockMvc.perform(get("/items/discription/1"))
                    .andExpect(status().isOk());
        }

        @Test
        @Order(5)
        @DisplayName("商品編集画面のアクセス")
        void showUpdateFrom() throws Exception {
            mockMvc.perform(get("/items/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/updateForm"));
        }

        @Test
        @Order(6)
        @DisplayName("商品編集エラー画面に遷移する")
        void showUpdateErrorFrom() throws Exception {
            mockMvc.perform(get("/items/update/error/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/updateErrorForm"));
        }

        @Test
        @Order(7)
        @DisplayName("編集ボタン押下し、商品一覧画面にリダイレクトする")
        void updateItem() throws Exception {

        }

        @Test
        @Order(8)
        @DisplayName("商品購入画面に遷移する")
        void showBuyFrom() throws Exception {
            mockMvc.perform(get("/items/buy/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/itemBuyForm"));
        }

        @Test
        @Order(9)
        @DisplayName("商品購入エラー画面に遷移する")
        void showBuyErrorFrom() throws Exception {
            mockMvc.perform(get("/items/buy/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/itemBuyForm"));
        }

        @Test
        @Order(10)
        @DisplayName("確認ボタン押下し、購入確認画面に遷移する")
        void buyItem() throws Exception {
            mockMvc.perform(get("/items/buy/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/itemBuyForm"));
        }

        @Test
        @Order(11)
        @DisplayName("購入確認画面に遷移")
        void showConfirmFrom() throws Exception {
            mockMvc.perform(get("/items/buy/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/itemBuyForm"));
        }

        @Test
        @Order(12)
        @DisplayName("購入確定ボタン押下し、購入完了画面に遷移する")
        void buyItemComplete() throws Exception {
            mockMvc.perform(get("/items/confirm/1"))
                    .andExpect(status().isOk());
        }

        @Test
        @Order(13)
        @DisplayName("購入完了画面のアクセス")
        void showItemComplete() throws Exception {
            mockMvc.perform(get("/items/complete/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/itemBuyComplete"));
        }
    }
}