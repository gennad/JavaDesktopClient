package com.gennadz;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				MainFrame frame;
				try {
					frame = new MainFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}

class MainFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	private SystemTray systemTray = SystemTray.getSystemTray();
	private TrayIcon trayIcon;
	
	public MainFrame() throws IOException {
		setTitle("This is header");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//buttons
		JButton siteButton = new JButton("Open the site");
		//adding panel to frame
		MainPanel panel = new MainPanel();
		panel.add(siteButton);
		add(panel);
		//creating action to button
		OpenSiteAction openSiteAction = new OpenSiteAction();
		siteButton.addActionListener(openSiteAction);
		//tray icon
		trayIcon = new TrayIcon(ImageIO.read(new File("trayicon.gif")), "Tray test");
		trayIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				setState(JFrame.NORMAL);
				removeTrayIcon();
			}
	    });
		addWindowStateListener(new WindowStateListener()
	    {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if(e.getNewState() == JFrame.ICONIFIED) {
					setVisible(false);
					addTrayIcon();
				}
			}
	    });
		//add popup menu to tray
		PopupMenu popupMenu = new PopupMenu();
	    MenuItem item = new MenuItem("Exit");
	    item.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    		System.exit(0);
	    	}
	    });
	    popupMenu.add(item);
	    trayIcon.setPopupMenu(popupMenu);
	}
	
	private void removeTrayIcon() {
		systemTray.remove(trayIcon);
	}
	private void addTrayIcon() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage("Tray test", "Window minimised to tray, double click to show", TrayIcon.MessageType.INFO);
	    }
	    catch(AWTException ex) {
	      ex.printStackTrace();
	    }
	}
}

class MainPanel extends JPanel {
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	public void paintComponent(Graphics g) {
		g.drawString("My program", MESSAGE_X, MESSAGE_Y);
	}
}

class OpenSiteAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//open browser
		URI url;
		try {
			url = new URI("www.yandex.ru");
			Desktop.getDesktop().browse(url);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}