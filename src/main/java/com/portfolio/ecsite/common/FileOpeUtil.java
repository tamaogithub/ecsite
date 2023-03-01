package com.portfolio.ecsite.common;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
     * アップロード実行処理
     * @param multipartFile
     * @return bytes
     */
    static public byte[] uploadAction(MultipartFile multipartFile) throws IOException {
        //ファイル名取得
        String fileName = multipartFile.getOriginalFilename();
        //アップロードファイルをバイト値に変換
        byte[] bytes = multipartFile.getBytes();
        return bytes;
    }

    /**
     * ファイルが画像なのか判定
     * @param file
     * @retun ture : ファイル画像 , false: ファイル画像意外
     */
    static public boolean isImageFileByImageIO(MultipartFile file) {

        //MultipartFileのfileName を取得
        String fileName = file.getOriginalFilename();

        try {
            // MultipartFileをFile形式に変換
            File convFile = new File(fileName);

            // ImageIOで、ファイルを読み込み
            if (convFile != null && convFile.isFile()) {
                // 拡張子を除いたファイル名を取得
                convFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(file.getBytes());
                fos.close();
                BufferedImage bi = ImageIO.read(convFile);

                // 引数に渡したFileが画像ファイル以外の場合、BufferedImageがnullで返ってくる。
                if (bi != null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException("Error uploading file.", e);
        }
    }

    /**
     * アップロードされたファイルをbase64ビッドに変換
     * @param image
     * @retun base64Data
     */
    static public String fileToBase64Data(MultipartFile image) throws IOException {
        //画像ファイル名取得
        String fileName = image.getOriginalFilename();
        byte[] bytes = uploadAction(image);
        String base64Data = Base64.getEncoder().encodeToString(bytes);
        return base64Data;
    }


    /**
     * アップロードされたファイルをbase64ビッドに変換
     * @retun base64Data
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
     * @retun
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
