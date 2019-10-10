package util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static BufferedImage resizeTrayPng(InputStream is, int outputWidth, int outputHeight) {
		try {
			boolean proportion = true;
			int fontSize = FontUtil.getFont().getSize();
			BufferedImage bi2 = ImageIO.read(is);
			int newWidth;
			int newHeight;
			// 判断是否是等比缩放
			if (proportion) {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
				double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
				// 根据缩放比率大的进行缩放控制
				double rate = rate1 < rate2 ? rate1 : rate2;
				newWidth = (int) (((double) bi2.getWidth(null)) / rate);
				newHeight = (int) (((double) bi2.getHeight(null)) / rate);
			} else {
				newWidth = outputWidth; // 输出的图片宽度
				newHeight = outputHeight; // 输出的图片高度
			}
			newWidth = newWidth + fontSize;
			BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = to.createGraphics();
			Image from = bi2.getScaledInstance(newWidth - fontSize, newHeight, BufferedImage.SCALE_AREA_AVERAGING);
			g2d.drawImage(from, fontSize/2, 0, null);
			g2d.dispose();
			return to;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
