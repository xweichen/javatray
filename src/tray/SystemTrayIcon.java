package tray;

import java.awt.Dimension;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class SystemTrayIcon extends TrayIcon {
	
	private JDialog dialog;
	
	/**
	 * 构造方法，创建带指定图像、工具提示和弹出菜单的 MyTrayIcon
	 * @param image 显示在系统托盘的图标
	 * @param ps	鼠标移动到系统托盘图标上的提示信息
	 * @param jMenu	弹出菜单
	 */
	public SystemTrayIcon(java.awt.Image image,String ps,JPopupMenu jMenu) {
		super(image, ps);
	    	
	    //初始化JDialog
	    dialog = new JDialog();
	    dialog.setUndecorated(true);//取消窗体装饰
	    dialog.setAlwaysOnTop(true);//设置窗体始终位于上方
	        
	    //设置系统图标大小为自动调整
	    this.setImageAutoSize(true);
	        
	    //为TrayIcon设置鼠标监听器
	    this.addMouseListener(new MouseAdapter() {
	    	@Override
	        public void mouseReleased(MouseEvent e) {
	        	//鼠标右键在组件上释放时调用，显示弹出菜单
	            if (e.getButton() == MouseEvent.BUTTON3 && jMenu != null) {
	            	//设置dialog的显示位置
	            	Dimension size = jMenu.getPreferredSize();
	    	        dialog.setLocation(e.getX() - size.width - 3, e.getY() - size.height-3);
	    	        dialog.pack();
	    	        dialog.setVisible(true);
	    	        //显示弹出菜单Jmenu
	    	        jMenu.show(dialog.getContentPane(), 0, 0);
	    	    }
	        }
	    });
	    
	    //为弹出菜单添加监听器
	    jMenu.addPopupMenuListener(new PopupMenuListener() {
	    	@Override
	    	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
	    	@Override
		    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	    		dialog.setVisible(false);
		    }
	    	@Override
		    public void popupMenuCanceled(PopupMenuEvent e) {
	    		dialog.setVisible(false);
		    }
	    });
	
	}
}
