package hello;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {

    @RequestMapping(value="/chart", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,
                             @RequestParam(value="data", defaultValue="") String data,
                             @RequestParam(value="w", defaultValue="200") String width,
                             @RequestParam(value="h", defaultValue="200") String height) throws Exception {


        response.setContentType("image/png");
//        response.setContentLength((int)file.length());

        OutputStream os = response.getOutputStream();

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix matrix = new MultiFormatWriter().encode(data,
                BarcodeFormat.QR_CODE, Integer.parseInt(width), Integer.parseInt(height), hints);

        MatrixToImageWriter.writeToStream(matrix, "png", os);
    }


//    @RequestMapping("/chart")
////    @ResponseBody
//    public ResponseEntity<InputStreamResource> chart(@RequestParam(value="data", defaultValue="") String data) throws IOException{
//
//
//        InputStream in = new FileInputStream(new File("/study/qr.png"));
//
//        int width = 200; // 图像宽度
//        int height = 200; // 图像高度
//        String format = "png";// 图像类型
//        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        BitMatrix matrix = new MultiFormatWriter().encode(data,
//                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
//
//
//
//        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
//
//
//        return ResponseEntity.ok()
////                .contentLength()
//                .contentType(MediaType.IMAGE_PNG)
//                .body(new InputStreamResource(new BufferedInputStream(image.get)));
//    }
}
