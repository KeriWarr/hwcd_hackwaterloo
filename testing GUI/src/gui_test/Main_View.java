package gui_test;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_View {
	
	final static int g = 96;       // grey color 1
	final static int g2 = g - g/2; // grey color 2
	static int posX = 0;
	static int posY = 0;
	
	private static JButton createSimpleButton(String text){
		  JButton button = new JButton(text);
		  button.setForeground(Color.WHITE);
		  Color c = new Color(g2,g2,g2);
		  button.setBackground(c);
		  button.setBorder(new LineBorder(c, 12));
		  return button;
	}
	
	public static void main(String[] args) {
			
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				// main frame of application
				final JFrame frame = new JFrame("Hello World");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(500, 300);
				frame.setUndecorated(true);
				
				frame.getContentPane().setBackground(new Color(g,g,g));
				
				// panel for closing and moving the main frame
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.NORTH);
				panel.setBackground(new Color(g2,g2,g2));
				panel.setLayout(new BorderLayout());
				
				// Movement
				panel.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						posX = e.getX();
						posY = e.getY();
					}
				});
				
				panel.addMouseMotionListener(new MouseAdapter() {
					public void mouseDragged(MouseEvent evt) {
						// sets frame position
						int color_x = avoid_same_g((abs(posX%100)));
						frame.getContentPane().setBackground(new Color(color_x, color_x, color_x));
						frame.setLocation(evt.getXOnScreen()-posX, evt.getYOnScreen()-posY);
					}

					private int avoid_same_g(int i) {
						if (i == g2) {
							return i - i/2;
						}
						return i;
					}

					private int abs(int i) {
						if (i < 0) {
							return -i;
						}
						return i;
					}
				});
				
				// label title
				JLabel lblHwcdHackwaterloo = new JLabel("   HWcd hackWaterloo");
				lblHwcdHackwaterloo.setForeground(new Color(255, 255, 255));
				lblHwcdHackwaterloo.setBackground(new Color(255, 255, 255));
				lblHwcdHackwaterloo.setFont(new Font("Arial", Font.BOLD, 12));
				panel.add(lblHwcdHackwaterloo, BorderLayout.CENTER);
				
				// close button
				JButton btnClose = createSimpleButton("CLOSE");
				btnClose.setFont(new Font("Arial", Font.BOLD, 11));
				panel.add(btnClose, BorderLayout.EAST);
				// event listener to button
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				
				// initialize original frame to screen
				frame.setVisible(true);
			}
		});
	}
}