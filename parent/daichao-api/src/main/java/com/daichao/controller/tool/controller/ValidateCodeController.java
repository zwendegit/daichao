package com.daichao.controller.tool.controller;

import com.daichao.common.SimpleRedisCache;
import com.daichao.constant.Global;
import com.daichao.controller.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by hook on 2017/9/1.
 * <p>
 */
@RestController
@RequestMapping("/validate")
@Api(tags = "2.validateManage", description = "验证码")
public class ValidateCodeController extends CommonController {

    @Resource
    private SimpleRedisCache cache;

    @RequestMapping(value="/creatValidateCode",method = RequestMethod.GET)
    @ApiOperation(value = "生成图片验证码", notes = "图片验证码")
    public void creatValidateCode(HttpServletRequest request, HttpServletResponse response,String phone) {
        try {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        int width = 60, height = 30;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        OutputStream os = response.getOutputStream();
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 设置备选验证码:包括"a-z"和数字"0-9"
        String base = "abcdefghijklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ123456789";
        int size = base.length();
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            int start = random.nextInt(size);
            String strRand = base.substring(start, start + 1);
            sRand += strRand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            g.drawString(strRand+"", 13 * i + 6, 25);
        }
        cache.put(Global.VALIDATE_DATE+phone, sRand.toLowerCase(), Global.VERIFICATION_CODE_VALID_PERIOD);
        g.dispose();
        ImageIO.write(image, "JPEG", os);
        os.flush();
        os.close();
        os = null;
        response.flushBuffer();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}