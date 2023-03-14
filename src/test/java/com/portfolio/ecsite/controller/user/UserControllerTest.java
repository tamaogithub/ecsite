package com.portfolio.ecsite.controller.user;

import com.portfolio.ecsite.repository.user.UserRepository;
import com.portfolio.ecsite.service.user.UserService;
import com.portfolio.ecsite.validation.UniqueUsernameValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    private MockMvc mockMvc;

    @Mock
    private BindingResult bindingResult;

    UniqueUsernameValidator uniqueUsernameValidator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
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
            mockMvc.perform(MockMvcRequestBuilders.get("/users")
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
            mockMvc.perform(get("/users/creationForm").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("users/creationForm")).andReturn();
        }

        @Test
        @Order(1)
        @DisplayName("ユーザー登録成功時のリダイレクト")
        void createUser() throws Exception {
            String username = "tom";
            when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
            boolean result = uniqueUsernameValidator.isValid(username, context);
            UserForms userForms = new UserForms();
            userForms.setUserName("testUser");
            userForms.setPassword("testPass");
            userForms.setAuthority("ADMIN");
            userForms.setCampany("DELL");
            userForms.setAddress("埼玉");
            userForms.setPhone("080-9999-0000");

            when(bindingResult.hasErrors()).thenReturn(false);

            mockMvc.perform(MockMvcRequestBuilders.post("/users")
                            .flashAttr("userForms", userForms)
                            .param("limit", "10")
                            .param("offset", "0"))
                    .andExpect(status().is3xxRedirection())
                    .andReturn();

            verify(userService).create("testUser", "testPass", "ADMIN", "DELL", "埼玉", "080-9999-0000");
        }

//        @Test
//        @Order(3)
//        @DisplayName("登録ボタン押下後、ユーザ一覧画面にリダイレクトする")
//        void createUser() {
//        }


//        @Test
//        @Order(4)
//        @DisplayName("ユーザー編集画面のアクセス")
//        void showUpdateFrom() throws Exception {
//            mockMvc.perform(get("/users/update/1"))
//                .andExpect(status().isOk())
//                .andExpect(model().hasNoErrors())
//                .andExpect(view().name("users/updateForm"));
//        }

//        @Test
//        @Order(5)
//        @DisplayName("ユーザー編集エラー画面のアクセス")
//        void showUserUpdateErrorFrom() throws Exception {
//            mockMvc.perform(get("/users/update//error/1"))
//                .andExpect(status().isOk())
//                .andExpect(model().hasNoErrors())
//                .andExpect(view().name("users/updateErrorForm"));
//        }

//        @Test
//        @Order(6)
//        @DisplayName("編集ボタン押下し、ユーザー一覧画面にリダイレクトする")
//        void updateUser() {
//        }
    }


}