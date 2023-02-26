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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    // MainControllerでItemServiceにMockオブジェクトをDIする
    @MockBean
    private ItemService itemService;

    @MockBean
    private UserService userService;

    final MockHttpServletRequestBuilder items = get("/items?limit=10&offset=0").accept(MediaType.TEXT_HTML);
    final MockHttpServletRequestBuilder creationForm = get("/items/creationForm").accept(MediaType.TEXT_HTML);
    final MockHttpServletRequestBuilder updateForm = get("/items/update/1").accept(MediaType.TEXT_HTML);

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        // @AutoConfigureMockMvcというアノテーションを使うとこの初期化は不要だが、
        // 問題が起きることもあるので手動で初期化している。
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        System.out.println("mockMvc初期化");
    }


    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("コントローラーの検証")
    class ControllerTest {

        @Test
        @Order(1)
        @DisplayName("商品一覧画面のアクセス")
        void createItemTest() throws Exception {
            mockMvc.perform(items)
            .andExpect(model().attributeExists(
                "itemList","total","page","totalPage", "startPage",
                "endPage","offset","preOffset","itemList","base64Data"))
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors())
            .andExpect(view().name("items/list"));
        }

        @Test
        @Order(2)

        @DisplayName("商品詳細画面のアクセス")
        void showDiscriptionFromTest() throws Exception {
            mockMvc.perform(creationForm)
            .andExpect(model().attributeExists("base64Data"))
            .andExpect(status().isOk())
            .andExpect(model().hasNoErrors());
//            .andExpect(view().name("items/list"));
        }

//        @Test
//        @Order(3)
//        @DisplayName("商品編集画面のアクセス")
//        void showUpdateFromTest() throws Exception {
//            mockMvc.perform(updateForm)
//                .andExpect(model().attributeExists("base64Data"))
//                .andExpect(status().isOk())
//                .andExpect(model().hasNoErrors())
//                .andExpect(view().name("items/updateForm"));
//        }


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