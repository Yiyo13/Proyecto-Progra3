package com.vehiculos.mvc.controllers;

import java.util.ArrayList;

import com.vehiculos.mvc.models.dao.DAOPropietario;
import com.vehiculos.mvc.models.vo.Propietario;
import com.vehiculos.mvc.views.propietarios.Form;
import com.vehiculos.mvc.views.propietarios.Index;

public class ControllerPropietarios extends Functions implements Controller<Propietario> {
	private ControllerPrincipal cp;
	private DAOPropietario dao;
	
	public ControllerPropietarios(ControllerPrincipal cp) {
		this.cp = cp;
		dao = new DAOPropietario();
	}

	@Override
	public void index() {
		Index v = new Index();
		addSearch(v.tBuscar,v.table,1,2,3);
		v.modelo.setDataVector(getData(),getColumns());
		
		v.btnNuevo.addActionListener(e->{
			create();
		});
		v.btnEditar.addActionListener(e->{
			int id = getSelectedID(v.table);
			edit(id);
		});
		v.btnEditar.addActionListener(e->{
			int id = getSelectedID(v.table);
			edit(id);
		});
		
		cp.vp.setContenido(v,"Lista de Propietarios");
		
	}

	@Override
	public void create() {
		Form v = new Form();
		v.btnRegresar.addActionListener(e->{
			index();
		});
		v.btnRegistrar.addActionListener(e->{
			String nombre = v.tNombre.getText();
			String telefono = v.tTelefono.getText();
			int edad = Integer.parseInt(v.tEdad.getText());
			Propietario item = new Propietario(nombre, telefono, edad);
			
			store(item);
		});
		cp.vp.setContenido(v,"Registrar Propietario");
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(int id) {
		Propietario item = dao.find(id);
		Form v = new Form();
		
		v.btnRegistrar.setText("Actualizar");
		v.tNombre.setText(item.getNombre());
		v.tTelefono.setText(item.getTelefono());
		v.tEdad.setText(item.getEdad()+"");
		v.btnRegresar.addActionListener(e->{
			index();
		});
		v.btnRegistrar.addActionListener(e->{
			String nombre = v.tNombre.getText();
			String telefono = v.tTelefono.getText();
			int edad = Integer.parseInt(v.tEdad.getText());
			Propietario editado = new Propietario(nombre, telefono, edad);
			
			update(editado,id);
		});
		cp.vp.setContenido(v,"Editar propietarios");
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[][] getData() {
		ArrayList<Propietario> list = dao.all();
		Object[][] data = new Object[list.size()][getColumns().length];
		int i = 0;
		for (Propietario item : list) {
			data[i][0] = item.getId();
			data[i][1] = item.getNombre();
			data[i][2] = item.getTelefono();
			data[i][3] = item.getEdad();
			i++;
		}
		return data;
	}

	@Override
	public String[] getColumns() {
		return new String[] {"ID","Nombre","Telefono","Edad"};
	}

	@Override
	public void store(Propietario item) {
		if(dao.store(item)) {
			System.out.println("Registro correcto");
		}else {
			System.err.println("Error");
		}
		index();
	}

	@Override
	public void update(Propietario item, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy(int id) {
		// TODO Auto-generated method stub
		
	}
}
