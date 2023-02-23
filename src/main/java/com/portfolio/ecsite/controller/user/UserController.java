package com.portfolio.ecsite.controller.user;

import com.portfolio.ecsite.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** １ページの表示数 */
    private final String limits = "10";

    /** ページネーションで表示するページ数 */
    private int showPageSize = 5;

    String currentPage = null;
    String preOffset = "0";

    //ユーザー一覧画面に遷移
    @GetMapping
    public String showList(@RequestParam HashMap<String, String> params,
                           Integer limit, Long offset, Model model) {
        //権限情報デバッグ用
        //var x = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int total = 0;

        // データ一覧を取得
        var entityList = userService.findAll(limit, offset);
        // データ総数を取得
        total = userService.findTotal();

        // パラメータを設定し、現在のページを取得する
        String currentOffset = params.get("offset");

        if (currentOffset.equals("0")) {
            //初期表示では1ページに設定
            currentPage = "1";
        } else {
            currentPage = String.valueOf(Integer.parseInt(currentOffset.substring(0, currentOffset.length() - 1)) + 1);
            preOffset = String.valueOf((Integer.parseInt(currentOffset) - 10));
        }

        // データ取得時の取得件数、取得情報の指定
        HashMap<String, String> search = new HashMap<String, String>();
        search.put("limit", limits);
        search.put("page", currentPage);

        // pagination処理
        // "総数/1ページの表示数"から総ページ数を割り出す
        int totalPage = (total + Integer.valueOf(limits) -1) / Integer.valueOf(limits);
        int page = Integer.valueOf(currentPage);
        // 表示する最初のページ番号を算出（今回は3ページ表示する設定）
        // (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
        int startPage = page - (page-1)%showPageSize;
        // 表示する最後のページ番号を算出
        int endPage = (startPage+showPageSize-1 > totalPage) ? totalPage : startPage+showPageSize-1;

        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("offset", currentOffset);
        model.addAttribute("preOffset", preOffset);
        model.addAttribute("userList", entityList);

        return "users/List";
    }

    //ユーザー登録画面に遷移
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute UserForms user) {
        return "users/creationForm";
    }

    //ユーザーの登録ボタン押下
    @PostMapping
    public String createUser(@ModelAttribute @Validated UserForms form,
                         BindingResult bindingResult) throws IOException {

        if(bindingResult.hasFieldErrors()){
            return showCreationForm(form);
        }

        //ユーザー名、パスワード登録
        userService.create(
                form.getUserName(),form.getPassword(), form.getAuthority(),
                form.getCampany(), form.getAddress(), form.getPhone());
        return "redirect:/users?limit=10&offset=0";
    }

    //ユーザー編集画面に遷移
    @GetMapping("/{userId}")
    public String showUpdateFrom(@PathVariable("userId") Long userId, Model model) {
        var entity = userService.find(userId);
        model.addAttribute("user", entity);

        return "users/updateForm";
    }

    //ユーザーの編集ボタン押下
    @PutMapping("/{userId}")
    public String update(@PathVariable("userId") Long userId,
                         @ModelAttribute @Validated UserForms form,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }
        userService.update(userId,
                form.getUserName(),form.getPassword(), form.getAuthority(),
                form.getCampany(), form.getAddress(), form.getPhone());
        return "redirect:/users?limit=10&offset=0";
    }
}

