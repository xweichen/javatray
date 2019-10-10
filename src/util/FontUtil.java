package util;

import javax.swing.UIManager;

public class FontUtil {

	public static java.awt.Font getFont() {
		java.awt.Font defaultFont = UIManager.getFont("Label.font");
		float adjustmentRatio = ArithUtil.div(java.awt.Toolkit.getDefaultToolkit().getScreenResolution(),96).floatValue();
		float newFontSize = defaultFont.getSize() * adjustmentRatio ; 
		return defaultFont.deriveFont(newFontSize);
	}
	
}
