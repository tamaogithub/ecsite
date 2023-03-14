package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.common.FileOpeUtil;
import com.portfolio.ecsite.controller.ItemsApi;
import com.portfolio.ecsite.service.item.ItemEntity;
import com.portfolio.ecsite.service.item.ItemService;
import com.portfolio.ecsite.service.user.UserEntity;
import com.portfolio.ecsite.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

/**
 * コントローラクラス
 * 「@SessionAttributes(types = ItemBuyForms.class)」により、
 * 生成したFormオブジェクトをセッションとしてもたせている
 */
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@SessionAttributes(types = ItemBuyForms.class)
public class ItemController implements ItemsApi {

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final UserService userService;

//    @Autowired
//    private SecurityContextHolder securityContextHolder;

    /** １ページの表示数 */
    private final String limits = "10";

    /** ページネーションで表示するページ数 */
    private int showPageSize = 5;

    String currentPage = null;
    String preOffset = "0";
    String payment = null;
    // NoImage画像の取得
    String base64Data = FileOpeUtil.noImageFileToBase64Data();

    /**
     * Formオブジェクトを初期化して返却する
     * @return Formオブジェクト
     */
    @ModelAttribute("itemBuyForms")
    public ItemBuyForms createItemBuyForms(){
        ItemBuyForms itemBuyForms = new ItemBuyForms();
        //支払い方法の初期値を設定する
        itemBuyForms.setPayment("1");

        return itemBuyForms;
    }

    /**
     * 商品一覧画面に遷移する
     * @params リクエストパラメータ
     * @params limit
     * @params offset
     * @params model
     * @params sessionStatus セッションステータス
     * @return list.htmlのテンプレート
     */
    @GetMapping
    public String showList(@RequestParam HashMap<String, String> params,
                            Integer limit, Long offset, Model model, SessionStatus sessionStatus) {

        //セッションオブジェクトを破棄
        sessionStatus.setComplete();

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

    /**
     * /商品登録画面に遷移に遷移する
     * @params itemForms
     * @params model
     * @return creationForm.htmlのテンプレート
     */
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute ItemForms itemForms, Model model) {
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        return "items/creationForm";
    }

    /**
     * 登録ボタン押下後、商品一覧画面にリダイレクトする
     * @params itemForms Formオブジェクト
     * @params bindingResult
     * @return list.html のテンプレートにリダイレクト
     */
    @PostMapping
    public String createItem(@ModelAttribute @Validated ItemForms itemForms,
                             BindingResult bindingResult, Model model) throws IOException {



        // フォームで渡されてきたアップロードファイルを取得
        MultipartFile image = itemForms.getItemImage();
        //画像ファイル名取得
        String fileName = image.getOriginalFilename();
        byte[] bytes = FileOpeUtil.uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);
        model.addAttribute("base64Data", base64Data);
        if (bindingResult.hasErrors()) {
            return showCreationForm(itemForms, model);
        }
        if(base64Data.isEmpty()){
            base64Data = null;
        }

        itemService.create(
                itemForms.getItemName(), itemForms.getDescription(), fileName,
                base64Data, itemForms.getCompany(), itemForms.getPrice(), itemForms.getStock(), null);

        return "redirect:/items?limit=10&offset=0";
    }

    /**
     * 商品詳細画面に遷移に遷移する
     * @params itemId
     * @params model
     * @return discriptionForm.html 商品詳細画面のテンプレート
     */
    @GetMapping("/discription/{itemId}")
    public String showDiscriptionFrom(@PathVariable("itemId") Long itemId, Model model) {

        var entity = itemService.find(itemId);
        var userEntity = userService.find(itemId);
        model.addAttribute("item", entity);
        model.addAttribute("userItem", userEntity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        return "items/discriptionForm";
    }

    /**
     * 商品編集画面に遷移に遷移する
     * @params itemForms Formオブジェクト
     * @return creationForm.htmlのテンプレート
     */
    @GetMapping("/update/{itemId}")
    public String showUpdateFrom(@PathVariable("itemId") Long itemId,
                                 @ModelAttribute ItemForms itemForms, Model model) {

        var entity = itemService.find(itemId);
        model.addAttribute("item", entity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);

        return "items/updateForm";
    }

    /**
     * 商品編集エラー画面に遷移する
     * @params itemForms Formオブジェクト
     * @return creationForm.htmlのテンプレート
     */
    @GetMapping("/update/error/{itemId}")
    public String showUpdateErrorFrom(@PathVariable("itemId") Long itemId,
                                      @ModelAttribute ItemForms itemForms, Model model) {

        var entity = itemService.find(itemId);
        model.addAttribute("item", entity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);

        return "items/updateErrorForm";
    }

    /**
     * 編集ボタン押下し、商品一覧画面にリダイレクトする
     * @params itemId
     * @params itemForms Formオブジェクト
     * @params bindingResult
     * @params model
     * @return list.html のテンプレート
     */
    @PreAuthorize("hasAuthority('MAKER')")
    @PostMapping("/update/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId,
                             @ModelAttribute @Validated ItemForms itemForms,
                             BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            return showUpdateErrorFrom(itemId, itemForms , model); //商品編集画面に遷移
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

    /**
     * 商品購入画面に遷移する
     * @params itemId
     * @params itemBuyForms Formオブジェクト
     * @params model
     * @return itemBuyForm.html 商品購入画面のテンプレート
     */
    @GetMapping("/buy/{itemId}")
    public String showBuyFrom(@PathVariable("itemId") Long itemId,
                              @ModelAttribute ItemBuyForms itemBuyForms, Model model) {

        ItemEntity entity = itemService.find(itemId);
        UserEntity userEntity = userService.find(itemId);

        model.addAttribute("itemBuy", entity);
        model.addAttribute("itemBuyForms", itemBuyForms);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        model.addAttribute("userItem", userEntity);
        return "items/itemBuyForm";
    }
    /**
     * 商品購入エラー画面に遷移する
     * @params itemId
     * @params itemBuyForms Formオブジェクト
     * @params model
     * @return itemBuyForm.html 商品購入エラー画面のテンプレート
     */
    @GetMapping("/buy/error/{itemId}")
    public String showBuyErrorFrom(@PathVariable("itemId") Long itemId,
                                   @ModelAttribute ItemBuyForms itemBuyForms, Model model) {

        var entity = itemService.find(itemId);
        model.addAttribute("itemBuy", entity);
        model.addAttribute("itemBuyForms", itemBuyForms);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        return "items/itemBuyErrorForm";
    }

    /**
     * 確認ボタン押下し、購入確認画面に遷移する
     * @params itemId
     * @params itemForms Formオブジェクト
     * @return itemBuyConfirm.html 購入確認画面のテンプレート
     */
    @PreAuthorize("hasAuthority('SHOP')")
    @PostMapping("/buy/{itemId}")
    public String buyItem(@PathVariable("itemId") Long itemId,
                          @ModelAttribute @Validated ItemBuyForms itemBuyForms,
                          BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            return showBuyErrorFrom(itemId, itemBuyForms, model); //商品購入エラー画面に遷移
        }
        var entity = itemService.find(itemId);
        var userEntity = userService.find(itemId);

        model.addAttribute("itemBuyConfirm", entity);
        model.addAttribute("itemBuyForms", itemBuyForms);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        model.addAttribute("userItemConfirm", userEntity);

        return "redirect:/items/confirm/{itemId}";
    }

    /**
     * 購入確認画面に遷移する
     * @params itemId
     * @params itemBuyForms Formオブジェクト
     * @params model
     * @return itemBuyForm.html 購入確認画面のテンプレート
     */
    @GetMapping("/confirm/{itemId}")
    public String showConfirmFrom(@PathVariable("itemId") Long itemId,
                                  @ModelAttribute @Validated ItemBuyForms itemBuyForms, Model model) {

        var entity = itemService.find(itemId);
        var userEntity = userService.find(itemId);

        model.addAttribute("itemBuyConfirm", entity);
        model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        model.addAttribute("userItemConfirm", userEntity);

        return "items/itemBuyConfirm";
    }

    /**
     * 購入確定ボタン押下し、購入完了画面に遷移する
     * @params itemId
     * @params itemBuyForms Formオブジェクト
     * @return itemBuyConfirm.html 購入確認画面のテンプレート
     */
    @PostMapping("/confirm/{itemId}")
    public String buyItemComplete(@PathVariable("itemId") Long itemId,
                                  @ModelAttribute ItemBuyForms itemBuyForms) throws IOException {

        //Authentication authentication = securityContextHolder.getContext().getAuthentication();

        //String username = authentication.getName();
//        User user = userService.findByUsername(username);
//        model.addAttribute("user", user);

//        Long userId = 1L;
        payment = itemBuyForms.getPayment();

        switch (payment) {
            case "1":
                payment = "INVOICE";
                break;
            case "2":
                payment = "BANK";
                break;
            case "3":
                payment = "CREDIT";
                break;
            default:
                payment = null;
        }

        var currentStock = itemService.getStock(itemId);
        var quantity =  itemBuyForms.getStock();

        // 現在の在庫数 - フォームで入力した個数
        Integer stock = currentStock - quantity;

        // 個数、支払い方法更新
        itemService.itemBuy(itemId, stock, payment);
        Integer itemStock = itemService.getStock(itemId);
        if (itemStock == 0) {
            //商品の削除
            itemService.delete(itemId);
        }

        //注文情報の登録
//        itemService.orderCreate(userId, itemId , quantity);

            return "redirect:/items/complete/{itemId}";
        }

    /**
     * 購入完了画面に遷移する
     * @params itemId
     * @params sessionStatus セッションステータス
     * @return itemBuyComplete.html 購入完了画面
     */
    @GetMapping("/complete/{itemId}")
    public String showItemComplete(@PathVariable("itemId") Long itemId, SessionStatus sessionStatus) {
        //セッションオブジェクトを破棄
        sessionStatus.setComplete();
        return "items/itemBuyComplete";
    }
}
