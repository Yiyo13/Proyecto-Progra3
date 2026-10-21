package com.vehiculos.mvc.controllers;

import java.util.ArrayList;

import com.vehiculos.mvc.models.dao.DAOPropietario;
import com.vehiculos.mvc.models.vo.Propietario;
import com.vehiculos.mvc.views.ViewPrincipal;

public class ControllerPrincipal {
	
	public ViewPrincipal vp;
	public ControllerPropietarios propietarios;
	

	public ControllerPrincipal() {
		vp = new ViewPrincipal();
		propietarios = new ControllerPropietarios(this);
	}
	public void init() {
		/*Propietario nuevo = new Propietario("Diana", "4557887", 15);
		
		//new DAOPropietario().store(nuevo);
		//update
		
		Propietario actualizado = new Propietario("JuanKA", "4557887", 15);
		//new DAOPropietario().update(actualizado, 2);
		
		//destroy
		new DAOPropietario().destroy(1);
		ArrayList<Propietario> data= new DAOPropietario().all();
		System.out.println(new DAOPropietario().find(3));
		System.out.println(data);
		//vp.init();
		*/
		vp.btnPropietarios.addActionListener(e->{
			propietarios.index();
		});
		vp.btnVehiculos.addActionListener(e->{
			
		});
		
		vp.init();
		//Vista inicial 
		
		propietarios.index();
		propietarios.edit(2);
	}

}
