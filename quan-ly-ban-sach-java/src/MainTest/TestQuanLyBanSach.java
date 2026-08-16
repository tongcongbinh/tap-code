package MainTest;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Controller.Connection;
import View.LoginForm;

public class TestQuanLyBanSach {
	public static void main(String[] args) {
		LoginForm loginForm = new LoginForm();
		loginForm.setVisible(true);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginForm(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
