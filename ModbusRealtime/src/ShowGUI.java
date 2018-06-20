import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.imageio.ImageIO;
//import java.awt.geom.Line2D;
//import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.*;

public class ShowGUI extends JFrame{
	int width=920;
    int height=570;
    static Color[] CircleC= {
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0),
    	new Color(255,0,0)
    };
    
    private Image backgroundImage;
    public void JPanelWithBackground(String fileName) throws IOException {
    	System.out.println("test!");
    	backgroundImage = ImageIO.read(new File(fileName));
    	System.out.println("test!!");
    }
	public ShowGUI() {
		setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        JPanel p = new JPanel() {
        	@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				try {
					JPanelWithBackground("TopView.JPG");
				}catch(Exception e) {}
				//Shape line = new Line2D.Double(3, 3, 303, 303);
				g2.drawImage(backgroundImage, 0, 0, null);
				g2.setPaint(new Color(0,0,0));
				Shape i = new Rectangle(60,60,110,300);
				
				g2.draw(new Rectangle(60, 60, 110, 400));
				g2.draw(new Rectangle(200, 60, 250, 100));
				g2.draw(new Rectangle(470, 60, 250, 100));
				g2.draw(new Rectangle(750, 60, 110, 400));
				//Shape circle = new Ellipse2D.Double(100, 100, 100, 100);
				g2.setPaint(CircleC[0]);
				//g2.setPaint(new Color(0,0,255));
				g2.fillOval(90, 350, 50, 50);
				g2.setPaint(CircleC[1]);
				g2.fillOval(90, 200, 50, 50);
				g2.setPaint(CircleC[2]);
				g2.fillOval(70, 95, 30, 30);
				g2.setPaint(CircleC[3]);
				g2.fillOval(130, 95, 30, 30);
				g2.setPaint(CircleC[4]);
				g2.fillOval(300, 85, 50, 50);
				g2.setPaint(CircleC[5]);
				g2.fillOval(570, 85, 50, 50);
				g2.setPaint(CircleC[6]);
				g2.fillOval(790, 70, 30, 30);
				g2.setPaint(CircleC[7]);
				g2.fillOval(790, 130, 30, 30);
				g2.setPaint(CircleC[8]);
				g2.fillOval(780, 200, 50, 50);
				g2.setPaint(CircleC[9]);
				g2.fillOval(780, 350, 50, 50);
				//Shape roundRect = new RoundRectangle2D.Double(20, 20, 250, 250, 5, 25);
				//g2.draw(line);
				//g2.draw(rect);
				//g2.draw(circle);
				//g2.draw(roundRect);
			}
        };
        setTitle("Show GUI");
		this.getContentPane().add(p);
	}
	
	public void ReDraw(Color C) {
		CircleC[2]=C;
		repaint();
	}
}
