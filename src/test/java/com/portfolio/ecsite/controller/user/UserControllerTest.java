package com.portfolio.ecsite.controller.user;

import com.portfolio.ecsite.service.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserController userController;

    // UserServiceにMockオブジェクトをDIする
    @MockBean
    private UserService userService;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Nested @Order(1) @DisplayName("UserControllerの検証")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ControllerTest {

        @Test
        @Order(1)
        @DisplayName("ユーザー一覧画面のアクセス")
        void showList() throws Exception {
            // テスト用のパラメータを作成
            HashMap<String, String> params = new HashMap<>();
            params.put("limit", "10");
            params.put("offset", "0");
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .param("limit", params.getOrDefault("limit", "10"))
                .param("offset", params.getOrDefault("offset", "0")))
                .andExpect(model().attributeExists("total", "page", "totalPage", "startPage", "endPage", "offset", "preOffset", "userList"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("users/list")).andReturn();
        }

        @Test
        @Order(2)
        @DisplayName("ユーザー登録画面のアクセス")
        void showCreationForm() throws Exception {
            MvcResult result = mockMvc.perform(get("/users/creationForm").accept(MediaType.TEXT_HTML))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("users/creationForm")).andReturn();
        }

        @Test
        @Order(3)
        @DisplayName("登録ボタン押下後、ユーザ一覧画面にリダイレクトする")
        void createUser() {
        }


        @Test
        @Order(4)
        @DisplayName("ユーザー編集画面のアクセス")
        void showUpdateFrom() throws Exception {
            mockMvc.perform(get("/users/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("users/updateForm"));
        }

        @Test
        @Order(5)
        @DisplayName("ユーザー編集エラー画面のアクセス")
        void showUserUpdateErrorFrom() throws Exception {
            mockMvc.perform(get("/users/update//error/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().hasNoErrors())
                    .andExpect(view().name("users/updateErrorForm"));
        }

        @Test
        @Order(6)
        @DisplayName("編集ボタン押下し、ユーザー一覧画面にリダイレクトする")
        void updateUser() {
        }
    }


}