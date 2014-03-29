package gui_test;

import java.awt.Color;
import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class Main_View {
		
	public static void main(String[] args) {
			
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Hello World");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(500, 300);
				frame.setUndecorated(true);
				int g = 96;
				frame.getContentPane().setBackground(new Color(g,g,g));
				
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.NORTH);
				int g2 = g - g/2;
				panel.setBackground(new Color(g2,g2,g2));
				
				JLabel lblHwcdHackwaterloo = new JLabel("HWcd hackWaterloo");
				lblHwcdHackwaterloo.setBackground(new Color(255, 255, 255));
				lblHwcdHackwaterloo.setFont(new Font("Arial", Font.BOLD, 12));
				panel.add(lblHwcdHackwaterloo);
				
				frame.setVisible(true);
			}
		});
	}
}