# javatray
java 托盘组件，解决高分辨率下显示过小问题，这是个示例项目

# 解决思路
java的tray组件位于awt包下，高分辨率下显示过小原因是没有自动缩放，因为高分辨率下系统一般设置的有缩放比例，因此通过获取系统的缩放比例之后手动修改相关字体及图标的缩放即可达到正常显示效果。

```
public static java.awt.Font getFont() {
	java.awt.Font defaultFont = UIManager.getFont("Label.font");
	float adjustmentRatio = ArithUtil.div(java.awt.Toolkit.getDefaultToolkit().getScreenResolution(),
        96).floatValue();
	float newFontSize = defaultFont.getSize() * adjustmentRatio ; 
	return defaultFont.deriveFont(newFontSize);
}

```
