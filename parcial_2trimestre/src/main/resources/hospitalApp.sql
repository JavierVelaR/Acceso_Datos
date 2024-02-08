drop database HospitalApp;
create database HospitalApp;
use HospitalApp;

create table cliente(
	id int primary key auto_increment,
	nombre varchar(20),
    apellidos varchar(40),
    ciudad varchar(20),
    direccion varchar(40),
    telefono int,
    edad int,
    historial varchar(50)
);

create table medico(
	id int primary key auto_increment,
    nombre varchar(20),
    apellidos varchar(40),
    especialidad varchar(40)
);

create table cita(
	id int primary key auto_increment,
    id_cliente int,
    id_medico int,
    fecha Date,
    hora Time,
    index id_cliente (id_cliente),
    index id_medico (id_medico)
);