package com.proyecto.init;

import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;

import com.proyecto.mvc.controllers.ControllerPrincipal;

public class Main {

	public static void main(String[] args) {
		FlatGradiantoNatureGreenIJTheme.setup();
		new ControllerPrincipal().init();

	}

}