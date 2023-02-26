package com.portfolio.ecsite.controller.item;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        @Test @Order(1) @DisplayName("商品一覧画面のアクセス")
        void showListTest() throws Exception {
            MvcResult result = mockMvc.perform(get("/items?limit=10&offset=0").accept(MediaType.TEXT_HTML))
            .andExpect(model().attributeExists(
                "itemList","total","page","totalPage", "startPage",
                "endPage","offset","preOffset","itemList","base64Data"))
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("items/list")).andReturn();
        }

        @Test @Order(2) @DisplayName("商品登録画面のアクセス")
        void showCreationFormTest() throws Exception {
            MvcResult result = mockMvc.perform(get("/items/creationForm").accept(MediaType.TEXT_HTML))
            .andExpect(model().attributeExists("base64Data"))
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("items/creationForm")).andReturn();
        }
        @Test @Order(3) @DisplayName("商品登録画面の「登録」ボタンを押下後、商品一覧画面にリダイレクト")
        void createItemTest() throws Exception {
            var itemFroms = new ItemForms("歯ブラシ","歯ブラシ（極細）",null, null ,"LION",198,3,null);
            var objectMapper = new ObjectMapper();
            mockMvc.perform(
                post("/items/creationForm")
                        .content(objectMapper.writeValueAsString(itemFroms))  // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Content Typeを指定
                ).andExpect(status().isCreated());
//                    .andExpect(status().isOk())
//                    .andExpect(model().hasNoErrors())
//                    .andExpect(view().name("items/creationForm")).andReturn();
        }

        @Test @Order(4) @DisplayName("購入確認画面のアクセス")
        void showConfirmFromTest() throws Exception {
            mockMvc.perform(get("/items/confirm/1"))
                    .andExpect(status().isOk());
        }

        @Test @Order(5) @DisplayName("商品詳細画面のアクセス")
        void showDiscriptionFromTest() throws Exception {
            mockMvc.perform(get("/items/discription/1"))
                    .andExpect(status().isOk());
        }

        @Test @Order(6) @DisplayName("購入完了画面のアクセス")
        void buyItemCompleteTest() throws Exception {
            mockMvc.perform(get("/items/complete/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("items/itemBuyComplete"));
        }


    }

//        @Nested
//        @Order(2)
//        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//        class 商品詳細画面のアクセス {
//            final MockHttpServletRequestBuilder creationForm = get("/creationForm")
//                    .accept(MediaType.TEXT_HTML);
//
//            @BeforeEach
//            void setup() {
//                // @AutoConfigureMockMvcというアノテーションを使うとこの初期化は不要だが、
//                // 問題が起きることもあるので手動で初期化している。
//                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//                System.out.println("mockMvc初期化");
//            }

//            @Disabled //テストの無効化
//            @Test
//            @Order(1)
//            void モデルが格納されているオブジェクトを確認() throws Exception {
//                // http:localhost:8080/にアクセスした場合のテストを行う
//                mockMvc.perform(creationForm)
//                // HTTPステータスがOKであることを確認
//                .andExpect(status().isOk());
//
//                System.out.println("商品詳細画面のアクセス");
//            }

}
//    @Test
//    void createItemBuyForms() {
//    }
//
//    @Test
//    void showList() {
//    }
//
//    @Test
//    void showCreationForm() {
//    }

    //に /items/{itemId} GET メソッドでリクエストを送信し、HTTP ステータスとして 200 が返ってくることをテスト
//    @Test
//    @DisplayName("get Book, should return expected Book")
//    void createItem() {
////        int itemId = 1;
////        this.mvc.perform(get("/item/{itemId}")).andExpect(status().isOk());
//    }

//    @Test
//    void showDiscriptionFrom() {
//    }
//
//    @Test
//    void showUpdateFrom() {
//    }
//
//    @Test
//    void showUpdateErrorFrom() {
//    }
//
//    @Test
//    void updateItem() {
//    }
//
//    @Test
//    void showBuyFrom() {
//    }
//
//    @Test
//    void showBuyErrorFrom() {
//    }
//
//    @Test
//    void buyItem() {
//    }
//
//    @Test
//    void showConfirmFrom() {
//    }
//
//    @Test
//    void buyItemComplete() {
//    }
//
//    @Test
//    void showItemComplete() {
//    }
//}