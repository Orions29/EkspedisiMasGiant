package com.github.orions29.ekspedisi.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Project: EkspedisiMasRoi
 * Package: com.github.orions29.ekspedisi.utils
 * <p>
 * Utility Class buat nge-generate QR Code
 * </p>
 *
 * <hr>
 * <table border="0">
 * <tr><td><b>Author</b></td><td>: gianabif14</td></tr>
 * <tr><td><b>Date</b></td><td>: 5 Juni 2026</td></tr>
 * <tr><td><b>Time</b></td><td>: 19:07</td></tr>
 * </table>
 * <hr>
 *
 * @author gianabif14
 * @since 1.0
 */
public class QrGeneratorUtil {

    private static final Logger log = LoggerFactory.getLogger(QrGeneratorUtil.class);

    private static final String QR_DIRECTORY = "qr_result";

    public static void generateQrCode(String resiId) {
        try {
            // Pastikan folder qr_result ada
            Path pathDir = Paths.get(QR_DIRECTORY);
            if (!Files.exists(pathDir)) {
                Files.createDirectories(pathDir);
            }

            // Path untuk file gambar QR
            String filePath = QR_DIRECTORY + "/" + resiId + ".png";
            Path pathFile = FileSystems.getDefault().getPath(filePath);

            // Generate QR Code menggunakan ZXing
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(resiId, BarcodeFormat.QR_CODE, 300, 300);

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", pathFile);

            log.info("QR Code untuk resi {} berhasil di-generate.", resiId);

        } catch (WriterException | IOException e) {
            log.error("Gagal men-generate QR Code untuk resi {}", resiId, e);
        }
    }
}
