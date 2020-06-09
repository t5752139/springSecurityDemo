package com.imooc.security.validata;


import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ImageCodeController {

    public static final String IMAGE_KEY = "SEESION_KEY_IMAGE+CODE";
    //seesion 工具类
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), IMAGE_KEY, imageCode);

        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());

    }

    private ImageCode createImageCode(HttpServletRequest request) {
        int width = 67;
        int height = 23;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor( getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times new roman", Font.ITALIC, 20));
        g.setColor( getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(100), 20 + random.nextInt(110), random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);

        }
        g.dispose();
        return new ImageCode(image, sRand, 60);


    }

    private Color getRandColor(int i, int bc) {

        Random random = new Random();
        if (i > 255)
            i = 255;
        if (bc>255)
            bc=255;
        int r = i+random.nextInt(bc - i);
        int g= i+random.nextInt(bc - i);
        int b = i+random.nextInt(bc - i);
        return new Color(r,g,b);

    }
}
