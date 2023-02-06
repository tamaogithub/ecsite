package com.portfolio.ecsite.controller.item;

import com.portfolio.ecsite.common.FileOpeUtil;
import com.portfolio.ecsite.controller.ItemsApi;
import com.portfolio.ecsite.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController implements ItemsApi {

    private final ItemService itemService;

    /** １ページの表示数 */
    private final String limits = "10";

    /** ページネーションで表示するページ数 */
    private int showPageSize = 5;

    String currentPage = null;
    String preOffset = "0";

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

        //画像ファイルの指定
        File fileImg = new File("c:\\tmp\\noImage.png");
        try {
            byte[] byteImg = Files.readAllBytes(fileImg.toPath());
            String base64Data = Base64.getEncoder().encodeToString(byteImg);
            model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        }catch(IOException e) {
            return null;
        }

        return  "items/list";
    }

    //商品登録画面に遷移
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute ItemForms itemForms) {
        return "items/creationForm";
    }

    //商品の登録ボタン押下
    @PostMapping
    public String createItem(@Validated ItemForms itemForms, BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            return showCreationForm(itemForms);
        }

        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile image = itemForms.getItemImage();

        //ファイルが空でなく、画像形式ファイルでない場合
        if (!image.isEmpty() && !FileOpeUtil.isImageFileByImageIO(image)) {
            //エラーメッセージと共にリダイレクトする
            model.addAttribute("errorMsg", "追加されたファイルは画像ではありません");
            return "redirect:/items/creationForm";
        }

        //ファイル名取得
        String fileName = image.getOriginalFilename();
        byte[] bytes  = FileOpeUtil.uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);

        var entity = itemService.create(
                itemForms.getItemName(), itemForms.getDescription(), fileName
                ,base64Data, itemForms.getCompany(), itemForms.getPrice(), itemForms.getStock());

        return "redirect:/items?limit=10&offset=0";
    }

    //商品編集画面に遷移
    @GetMapping("/{itemId}")
    public String showUpdateFrom(@PathVariable("itemId") Long itemId, Model model) {
        var entity = itemService.find(itemId);
//        var dto = toItemDTO(entity);
        model.addAttribute("item", entity);

        return "items/updateForm";
    }

    //商品の編集ボタン押下
    @PutMapping("/{itemId}")
    public String update(@PathVariable("itemId") Long itemId,
                         @ModelAttribute @Validated ItemForms form,
                         BindingResult bindingResult,
                         Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }

        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile image = form.getItemImage();

        byte[] bytes  = FileOpeUtil.uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);

        itemService.update(itemId, form.getItemName(), form.getDescription(), form.getFileName(),
                base64Data, form.getCompany(), form.getPrice(), form.getStock());
        return "redirect:/items?limit=10&offset=0";
    }

//    @Override
//    public ResponseEntity<ItemDTO> createItem(ItemForm form) {
//        var entity = itemService.create(form.getItemName(), form.getDescription(),
//                form.getCompany(), form.getPrice(), form.getStock());
//        var dto = toItemDTO(entity);
//
//        return ResponseEntity
//                .created(URI.create("/items/" + dto.getId()))
//                .body(dto);
//    }



//    @NotNull
//    private ItemDTO toItemDTO(ItemEntity itemEntity) {
//        var itemDTO = new ItemDTO();
//        itemDTO.setId(itemEntity.getId());
//        itemDTO.setItemName(itemEntity.getItemName());
//        itemDTO.setDescription(itemEntity.getDescription());
//        itemDTO.setDescription(itemEntity.getDescription());
//        itemDTO.setCompany(itemEntity.getCompany());
//        itemDTO.setPrice(itemEntity.getPrice());
//        itemDTO.setStock(itemEntity.getStock());
//        return itemDTO;
//    }
}
