drop database if exists db_vehiculos;
create database db_vehiculos;
use db_vehiculos;

create table propietarios (
	id_propietario int auto_increment primary key,
    nombre varchar(200),
    telefono varchar(20),
    edad int
);

create table vehiculos (
	id_vehiculo int auto_increment primary key,
    placa varchar(10),
    id_propietario int,
    foreign key (id_propietario) references propietarios (id_propietario)
    on update cascade on delete cascade
);


insert into propietarios (nombre, telefono, edad) values 
('Juan', '8888-8888', 56),
('Ana', '7777-7777', 27),
('Luis', '5555-5555', 40),
('Mauren', '4444-4444', 35)
;


insert into vehiculos (placa, id_propietario) values 
('123', 1),
('456', 2),
('789', 3),
('987', 1),
('654', 1),
('321', 3),
('321', 3)
;