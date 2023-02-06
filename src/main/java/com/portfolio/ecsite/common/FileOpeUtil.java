package com.portfolio.ecsite.common;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ファイル操作用のユーティリティクラス
 * @author　Akira.Tamao
 * @version　1.0.0
 */
public class FileOpeUtil {

    /**
     * アップロード実行処理
     * @param multipartFile
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
     */
    static public boolean isImageFileByImageIO(MultipartFile file) {

        try {
            // MultipartFileをFile形式に変換
            File convFile = new File(file.getOriginalFilename());
            // 拡張子を除いたファイル名を取得
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            // ImageIOで、ファイルを読み込み
            if (convFile != null && convFile.isFile()) {
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
}
