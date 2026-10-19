package com.gestionseries.mvc.controllers;

import com.gestionseries.mvc.views.ViewPrincipal;

public class ControllerPrincipal {

	public ViewPrincipal vp;
	public ControllerSeries series;

	public ControllerPrincipal() {
		vp = new ViewPrincipal();
		series = new ControllerSeries(this);
	}

	public void init() {

		vp.init();

		// Vista inicial
		series.index();
	}

}