package csu.web.mypetstore.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int originalWidth = 200;
        int originalHeight = 50;

        // 设置缩放比例
        double scale = 0.75; // 75% 的缩放比例
        int width = (int) (originalWidth * scale);
        int height = (int) (originalHeight * scale);

        // 创建一个图像缓冲区
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        // 生成随机字符
        String captchaText = generateRandomString(5); // 生成包含5个字符的随机字符串

        // 将验证码存储在Session中
        HttpSession session = req.getSession();
        session.setAttribute("captcha", captchaText);

        // 设置
        g2d.setFont(new Font("SansSerif", Font.BOLD, (int) (24 * scale))); // 根据比例缩小字体大小
        g2d.setColor(Color.black);

        // 绘制验证码
        g2d.drawString(captchaText, (int) (50 * scale), (int) (30 * scale));

        // 添加噪音
        addNoise(g2d, (int) (width * scale), (int) (height * scale)); // 使用缩小后的尺寸添加噪声

        // 设置响应内容类型
        resp.setContentType("image/png");

        OutputStream os = resp.getOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        os.close();
    }

    // 生成随机字符串
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    // 添加噪音
    private void addNoise(Graphics2D g2d, int width, int height) {
        Random random = new Random();
        for (int i = 0; i < 75; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int size = random.nextInt((int)(5 * (0.75)));
            g2d.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
            g2d.fillRect(x, y, size, size);
        }
    }
}