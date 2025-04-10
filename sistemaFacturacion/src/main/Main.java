package main;

import gestion.gui.Login;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Login window = new Login();
			window.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
