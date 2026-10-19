package com.gestionseries.init;

import javax.swing.UIManager;

import com.gestionseries.mvc.controllers.ControllerPrincipal;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ControllerPrincipal().init();
	}

}