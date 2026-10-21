package com.vehiculos.init;

import javax.swing.UIManager;

import com.vehiculos.mvc.controllers.ControllerPrincipal;
import com.vehiculos.mvc.models.Conexion;
import com.vehiculos.mvc.views.ViewPrincipal;

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

