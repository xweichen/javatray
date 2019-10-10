package tray;

import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.FontUtil;
import util.ImageUtil;


public class TrayExample extends Application {
	
	private SystemTrayIcon trayIcon;
	
    @Override
    public void start(Stage primaryStage) {
        try {
        	primaryStage.setTitle("托盘测试");
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            enableTray(primaryStage);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void enableTray(final Stage stage) {
		// 双击事件方法
		MouseListener mouseClick = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
					if (stage.isShowing()) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								stage.hide();
							}
						});
					}else {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								stage.show();
								stage.setIconified(false);
								stage.toFront();
							}
						});
					}
				}
			}
		};

		try {
			SystemTray tray = SystemTray.getSystemTray();
			
			java.awt.Font derivedFont = FontUtil.getFont();
			int fontSize = derivedFont.getSize();
			int iconSize = new Double(fontSize * 1.5).intValue();
			
			JPopupMenu popupMenu = new JPopupMenu();
            
            
            JMenuItem settingMenu = new JMenuItem("设置");
            settingMenu.setPreferredSize(new Dimension(fontSize * 8, fontSize * 2 ));
            settingMenu.setIcon(new ImageIcon(ImageUtil.resizeTrayPng(getClass().getResourceAsStream("/assets/settings.png"), iconSize, iconSize)));
            settingMenu.setFont(derivedFont);
            settingMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("设置");
				}
			});
            popupMenu.add(settingMenu);
            
            //退出菜单
        	JMenuItem exitMenu = new JMenuItem("退出");
			exitMenu.setPreferredSize(new Dimension(fontSize * 12, fontSize * 2 ));
			exitMenu.setIcon(new ImageIcon(ImageUtil.resizeTrayPng(getClass().getResourceAsStream("/assets/exit.png"), iconSize, iconSize)));
			exitMenu.setFont(derivedFont);
            exitMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								SystemTray.getSystemTray().remove(trayIcon);
								stage.hide();
								Platform.exit();
						        System.exit(0);
							}
						});
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
            popupMenu.add(exitMenu);

            trayIcon = new SystemTrayIcon(ImageIO.read(getClass().getResourceAsStream("/assets/tray.png")), "桌面工具", popupMenu);
			trayIcon.setToolTip("桌面工具");
			tray.add(trayIcon);
			trayIcon.addMouseListener(mouseClick);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}