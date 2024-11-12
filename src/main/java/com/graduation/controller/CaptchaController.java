package com.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@Controller
public class CaptchaController {
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        int width = 160;
        int height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        Random random = new Random();

        // 填充背景色
        g.setColor(new Color(230, 230, 230));
        g.fillRect(0, 0, width, height);

        // 生成验证码字符
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char ch = chars.charAt(random.nextInt(chars.length()));
            captcha.append(ch);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            g.setColor(new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200)));
            g.drawString(String.valueOf(ch), 30 * i + 10, 30);
        }

        // 将验证码保存到 session（或其他存储方式）
        request.getSession().setAttribute("captcha", captcha.toString());
        //session.setAttribute("captcha", captcha.toString());

        //System.out.println("captcha session："+ session.getId());
        //System.out.println("生成的验证码为："+captcha.toString());
        // 设置响应头
        //response.setHeader("session_key", session.getId());

        // 画干扰线
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        g.dispose();

        // 设置响应内容类型
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
        response.getOutputStream().flush();
        System.out.println(captcha);
    }
}
