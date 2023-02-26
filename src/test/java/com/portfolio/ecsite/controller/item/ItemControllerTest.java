package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.service.item.ItemService;
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

    final MockHttpServletRequestBuilder items = get("/items?limit=10&offset=0")
            .accept(MediaType.TEXT_HTML);

    final MockHttpServletRequestBuilder creationForm = get("/creationForm")
            .accept(MediaType.TEXT_HTML);

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
    class コントローラーの検証 {

        @Test
        @Order(1)
        void 商品一覧画面のアクセス() throws Exception {
        // http:localhost:8080/にアクセスした場合のテストを行う
        mockMvc.perform(items)
            // modelに下記の名前でオブジェクトが格納されていることを確認
            .andExpect(model().attributeExists(
                    "itemList","total","page","totalPage", "startPage",
                    "endPage","offset","preOffset","itemList","base64Data"))
                // HTTPステータスがOKであることを確認
                .andExpect(status().isOk())
                // Modelオブジェクトにエラーが無いことを確認
                .andExpect(model().hasNoErrors())
                // 次画面の遷移先がlist.htmlであることを確認
                .andExpect(view().name("items/list"));
            System.out.println("商品一覧画面のアクセス");
        }

        @Test
        @Order(2)
        void 商品詳細画面のアクセス() throws Exception {
            // http:localhost:8080/にアクセスした場合のテストを行う
            mockMvc.perform(creationForm)
                    // HTTPステータスがOKであることを確認
                    .andExpect(status().isOk());

            System.out.println("商品詳細画面のアクセス");
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