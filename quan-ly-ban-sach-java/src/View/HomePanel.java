package View;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img_map = new ImageIcon(QuanLyForm.class.getResource("/pictures/map.png")).getImage()
			.getScaledInstance(1076, 551, Image.SCALE_SMOOTH);
	private Image img_statistical = new ImageIcon(QuanLyForm.class.getResource("/pictures/statistical.png")).getImage()
			.getScaledInstance(103, 103, Image.SCALE_SMOOTH);
	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setBorder(new LineBorder(Color.WHITE, 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);
		
		JLabel lb_map = new JLabel("");
		lb_map.setBounds(10, 11, 1076, 551);
		add(lb_map);
		lb_map.setIcon(new ImageIcon(img_map));
				
	}
	private class PanleButtonMouseAdpter extends MouseAdapter{
		JPanel panel;
		public PanleButtonMouseAdpter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(51, 140, 140));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(51, 153, 153));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(51, 165, 165));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(51, 140, 140));
		}
	}

}
