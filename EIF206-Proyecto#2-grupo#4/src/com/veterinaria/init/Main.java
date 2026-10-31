package com.veterinaria.init;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.veterinaria.mvc.controllers.ControllerPrincipal;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ControllerPrincipal().init();
	}

}
