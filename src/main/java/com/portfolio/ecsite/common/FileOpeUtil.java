package com.portfolio.ecsite.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * ファイル操作用のユーティリティクラス
 * @author　Akira.Tamao
 * @version　1.0.0
 */
public class FileOpeUtil {

    private static String base64Data;


    /**
     * ファイル操作用のユーティリティクラス
     * @param file
     * @return bytes
     * @throws IOException
     */
    static public byte[] uploadAction(MultipartFile file) throws IOException {

        //アップロードファイルをバイト値に変換
        byte[] bytes = file.getBytes();
        return bytes;
    }

    /**
     * アップロードされたファイルをbase64ビッドに変換
     * @param image
     * @return base64Data
     * @throws IOException
     */
    static public String fileToBase64Data(MultipartFile image) throws IOException {
        //画像ファイル名取得
        byte[] bytes = uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);
        return base64Data;
    }


    /**
     * アップロードされたファイルをbase64ビッドに変換
     * @return base64Data
     */
    static public String noImageFileToBase64Data(){
        //画像ファイルの指定
        File fileImg = new File("c:\\tmp\\noImage.png");
        try {
            byte[] byteImg = Files.readAllBytes(fileImg.toPath());
            base64Data = Base64.getEncoder().encodeToString(byteImg);
        } catch (IOException e) {
            return null;
        }
        return base64Data;
    }

    /**
     * アップロードされたファイルの拡張子を取得し画像かどうか判定する
     * @param image
     * @return bool値
     */
    static public Boolean isImageMimeType(MultipartFile image) {
        String contentType = image.getContentType();

        //ファイルが空でなく、画像形式ファイルでない場合
        if (!image.isEmpty() && contentType.contains(MimeTypes.MIME_IMAGE)){
            return false;
        }
        return true;
    }
}
