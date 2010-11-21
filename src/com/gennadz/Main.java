package com.gennadz;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				MainFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class MainFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	public MainFrame() {
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