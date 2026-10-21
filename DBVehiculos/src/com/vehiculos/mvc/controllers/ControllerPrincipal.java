package com.vehiculos.mvc.controllers;

import java.util.ArrayList;

import com.vehiculos.mvc.models.dao.DAOPropietario;
import com.vehiculos.mvc.models.vo.Propietario;
import com.vehiculos.mvc.views.ViewPrincipal;

public class ControllerPrincipal {
	
	public ViewPrincipal vp;
	public ControllerPropietarios propietarios;
	public ControllerVehiculos vehiculos;
	

	public ControllerPrincipal() {
		vp = new ViewPrincipal();
		propietarios = new ControllerPropietarios(this);
		vehiculos = new ControllerVehiculos(this);
	}
	public void init() {
		vp.btnPropietarios.addActionListener(e->{
			propietarios.index();
		});
		vp.btnVehiculos.addActionListener(e->{
			vehiculos.index();
		});
		
		vp.init();
		
		propietarios.index();
	}

}
