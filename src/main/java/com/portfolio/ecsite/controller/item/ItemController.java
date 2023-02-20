package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.common.FileOpeUtil;
import com.portfolio.ecsite.controller.ItemsApi;
import com.portfolio.ecsite.service.item.ItemService;
import com.portfolio.ecsite.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController implements ItemsApi {

    private final ItemService itemService;

    private final UserService userService;
    /** １ページの表示数 */
    private final String limits = "10";

    /** ページネーションで表示するページ数 */
    private int showPageSize = 5;

    String currentPage = null;
    String preOffset = "0";
    // NoImage画像の取得
    String base64Data = FileOpeUtil.noImageFileToBase64Data();

    //商品一覧画面に遷移
    @GetMapping
    public String showList(@RequestParam HashMap<String, String> params,
                            Integer limit, Long offset, Model model) {
        int total = 0;

        // データ一覧を取得
        var entityList = itemService.findAll(limit, offset);
        // データ総数を取得
        total = itemService.findTotal();

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
        model.addAttribute("itemList", entityList);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);

        return  "items/list";
    }

    //商品登録画面に遷移
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute ItemForms itemForms) {
        return "items/creationForm";
    }

    //商品の登録ボタン押下
    @PostMapping
    public String createItem(@ModelAttribute @Validated ItemForms itemForms,
                             BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return showCreationForm(itemForms);
        }

        // フォームで渡されてきたアップロードファイルを取得
        MultipartFile image = itemForms.getItemImage();
        //画像ファイル名取得
        String fileName = image.getOriginalFilename();
        byte[] bytes = FileOpeUtil.uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);
        if(base64Data.isEmpty()){
            base64Data = null;
        }

        itemService.create(
                itemForms.getItemName(), itemForms.getDescription(), fileName,
                base64Data, itemForms.getCompany(), itemForms.getPrice(), itemForms.getStock(), null);

        return "redirect:/items?limit=10&offset=0";
    }

    //商品編集画面に遷移
    @GetMapping("/update/{itemId}")
    public String showUpdateFrom(@PathVariable("itemId") Long itemId,
                                 @ModelAttribute ItemForms itemForms, Model model) {

        var entity = itemService.find(itemId);
        model.addAttribute("item", entity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);

        return "items/updateForm";
    }

    //商品の編集ボタン押下
//    @PreAuthorize("hasAuthority('MAKER')")
    @PutMapping("/update/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId,
                             @ModelAttribute @Validated ItemForms itemForms,
                             BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
//            return "items/updateForm";
//            return showUpdateFrom(itemId, itemForms ,bindingResult, model); //商品編集画面に遷移
            return showCreationForm(itemForms); //商品登録画面に遷移
        }

        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile image = itemForms.getItemImage();

        String fileName = itemForms.getFileName();
        byte[] bytes = FileOpeUtil.uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);
        if(base64Data.isEmpty()){
            itemService.updateExpectItemImage(itemId, itemForms.getItemName(), itemForms.getDescription(), fileName,
                    itemForms.getCompany(), itemForms.getPrice(), itemForms.getStock());
        } else {
            itemService.update(itemId, itemForms.getItemName(), itemForms.getDescription(), fileName,
                    base64Data, itemForms.getCompany(), itemForms.getPrice(), itemForms.getStock());
        }
        return "redirect:/items?limit=10&offset=0";
    }

    //商品購入画面に遷移
    @GetMapping("/buy/{itemId}")
    public String showBuyFrom(@PathVariable("itemId") Long itemId,
                              @ModelAttribute ItemBuyForms itemBuyForms, Model model) {

        var entity = itemService.find(itemId);
        var userEntity = userService.find(itemId);

        model.addAttribute("itemBuy", entity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        model.addAttribute("userItem", userEntity);
        return "items/itemBuyForm";
    }

    //商品の購入ボタン押下し、商品の確認画面に遷移
    @PutMapping("/buy/{itemId}")
    public String buyItem(@PathVariable("itemId") Long itemId,
                          @ModelAttribute @Validated ItemBuyForms itemBuyForms,BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
//            return showCreationForm(itemForms);
        }
        var entity = itemService.find(itemId);
        var userEntity = userService.find(itemId);

        model.addAttribute("itemBuyConfirm", entity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        model.addAttribute("userItemConfirm", userEntity);

        return "items/itemBuyConfirm";

//        return "redirect:/items/confirm/{itemId}";
    }

    //商品の確認画面に遷移
//    @GetMapping("/confirm/{itemId}")
//    public String showConfirmFrom(@PathVariable("itemId") Long itemId, Model model) {
//
////        var entity = itemService.find(itemId);
////        var userEntity = userService.find(itemId);
////
////        model.addAttribute("itemBuyConfirm", entity);
////        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
////        model.addAttribute("userItemConfirm", userEntity);
//
//        return "items/itemBuyConfirm";
//    }

    //購入確定ボタン押下し、購入完了画面に遷移
    @PutMapping("/confirm/{itemId}")
    public String buyItemComplete(@PathVariable("itemId") Long itemId,
                          @ModelAttribute @Validated ItemBuyForms itemBuyForms,BindingResult bindingResult) throws IOException {

        itemService.itemBuy(itemId, itemBuyForms.getStock(), itemBuyForms.getPayment());
        int stock = itemService.getStock(itemId);
        if (stock == 0) {
            itemService.delete(itemId);
        }

        return "redirect:/items/complete/{itemId}";
    }

    //購入完了画面に遷移
    @GetMapping("/complete/{itemId}")
    public String showItemComplete(@PathVariable("itemId") Long itemId, Model model) {

        return "items/itemBuyComplete";
    }


   //商品詳細画面に遷移
    @GetMapping("/discription/{itemId}")
    public String showDiscriptionFrom(@PathVariable("itemId") Long itemId, Model model) {

        var entity = itemService.find(itemId);
        var userEntity = userService.find(itemId);
        model.addAttribute("item", entity);
        model.addAttribute("userItem", userEntity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);

        return "items/discriptionForm";
    }

}
