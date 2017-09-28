<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>

<%!//生成随机颜色
	Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>

<%
	//设置页面不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	// 设置图片的长宽
	int width = 60;
	int height = 28;
	
	//创建内存图像
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	// 获取图形上下文
	Graphics g = image.getGraphics();
	
	//创建随机类的实例
	Random random = new Random();
	
	// 设定图像背景色(因为是做背景，所以偏淡)
	g.setColor(getRandColor(random, 200, 250));
	g.fillRect(0, 0, width, height);
	
	//定义字体形式
	g.setFont(new Font("Times New Roman", Font.PLAIN, 22));
	
	//在图片背景上增加噪点
	g.setColor(getRandColor(random, 160, 200));
	for (int i = 0; i < 6; i++) {
		g.drawString("@@@@@@@@@@@@@@@@@@@@####################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$********************",
				0, 5 * (i + 2));
	}
	
	//取随机产生的认证码(4位数字)
	String sRand = "";
	for (int i = 0; i < 4; i++)
	{
		String rand = String.valueOf(random.nextInt(10));
		sRand += rand;
		//将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(rand, 13 * i + 6, 16);
	}
	
	//将认证码存入session
	session.setAttribute("rand", sRand);
	
	//使图像生效
	g.dispose();
	
	//输出图象到页面
	ImageIO.write(image, "JPEG", response.getOutputStream());
	response.flushBuffer();

	//解决页面报错
	out.clear();
	out = pageContext.pushBody();
%>
