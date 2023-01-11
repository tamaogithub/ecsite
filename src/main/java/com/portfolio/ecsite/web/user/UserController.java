package com.portfolio.ecsite.web.user;

import com.portfolio.ecsite.domain.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showList(Model model) {
        // 権限情報デバッグ用
        //var x = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("userList", userService.findAll());
        return "users/List";
    }

    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute UserForm user) {
        return "users/creationForm";
    }
    @PostMapping
    public String create(@Validated UserForm form, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return showCreationForm(form);
        }

        //ユーザー名、パスワード登録
        userService.create(form.getUsername(),form.getPassword(), form.getAuthority());
        return "redirect:/users";
    }
}
