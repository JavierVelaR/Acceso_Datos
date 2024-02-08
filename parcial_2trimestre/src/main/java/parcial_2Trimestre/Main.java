package parcial_2Trimestre;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		byte opcion;

		do {

			System.out.println("------ Menú ------");
			System.out.println("1. Gestionar pacientes");
			System.out.println("2. Gestionar médicos");
			System.out.println("3. Gestionar citas");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");

			do {
				opcion = Byte.parseByte(sc.nextLine());
			} while (opcion < 1 || opcion > 4);

			switch (opcion) {
			case 1:
				byte opcion1;
				do {
					System.out.println();
					System.out.println("------ Menú Pacientes ------");
					System.out.println("1. Insertar paciente");
					System.out.println("2. Actualizar paciente");
					System.out.println("3. Mostrar lista de pacientes");
					System.out.println("4. Eliminar paciente");
					System.out.println("5. Volver al menú principal");
					System.out.print("Seleccione una opción:\n");

					opcion1 = Byte.parseByte(sc.nextLine());

					switch (opcion1) {
					case 1:
						
						System.out.println("Nombre del cliente:");
						String nombre = sc.nextLine();
						
						System.out.println("Apellidos del cliente:");
						String apellidos = sc.nextLine();
						
						System.out.println("Ciudad del cliente:");
						String ciudad = sc.nextLine();
						
						System.out.println("Direccion del cliente:");
						String direccion = sc.nextLine();
						
						System.out.println("telefono del cliente:");
						int telefono = Integer.parseInt(sc.nextLine());
						
						System.out.println("Edad del cliente:");
						int edad = Integer.parseInt(sc.nextLine());
						
						System.out.println("Historial del cliente:");
						String historial = sc.nextLine();
						
						Cliente cliente = new Cliente(nombre, apellidos, ciudad, direccion, telefono, edad, historial);
						
						ClienteDao.agregarCliente(cliente);
						
						break;
					case 2:
						System.out.println("Dime el id del cliente a modificar");
						byte id = Byte.parseByte(sc.nextLine());
						
						System.out.println("Dime el nombre nuevo para el cliente");
						String nuevoNombreCliente = sc.nextLine();
						
						System.out.println("Dime el apellido nuevo para el cliente");
						String nuevosApellidosCliente = sc.nextLine();
						
						ClienteDao.modificarCliente(id, nuevoNombreCliente, nuevosApellidosCliente);
												
						break;
					case 3:
						ClienteDao.mostrarClientes();
						break;
					case 4:
						System.out.println("Dime el id del cliente a eliminar");
						byte idClienteEliminar = Byte.parseByte(sc.nextLine());
						
						ClienteDao.eliminarCliente(idClienteEliminar);
						
						break;
					case 5:
						System.out.println("Volviendo al menu...");
						break;
					}
				} while (opcion1 < 1 || opcion1 > 5);

				break;

			case 2:
				byte opcion2;
				do {
					System.out.println();
					System.out.println("------ Menú Médicos ------");
					System.out.println("1. Insertar medico");
					System.out.println("2. Actualizar medico");
					System.out.println("3. Mostrar lista de medicos");
					System.out.println("4. Eliminar medico");
					System.out.println("5. Volver al menú principal");
					System.out.print("Seleccione una opción:\n");

					opcion2 = Byte.parseByte(sc.nextLine());

					switch (opcion2) {
					case 1:
						
						System.out.println("Nombre del medico:");
						String nombre = sc.nextLine();
						
						System.out.println("Apellidos del medico:");
						String apellidos = sc.nextLine();
						
						System.out.println("Especialidad del medico:");
						String especialidad = sc.nextLine();
						
						
						Medico medico = new Medico(nombre, apellidos, especialidad);
						
						MedicoDao.agregarMedico(medico);
						
						break;
					case 2:
						System.out.println("Dime el id del medico a modificar");
						byte id = Byte.parseByte(sc.nextLine());
						
						System.out.println("Dime el nombre nuevo para el medico");
						String nuevoNombreMedico = sc.nextLine();
						
						System.out.println("Dime el apellido nuevo para el medico");
						String nuevosApellidosMedico = sc.nextLine();
						
						MedicoDao.modificarMedico(id, nuevoNombreMedico, nuevosApellidosMedico);
						
						break;
					case 3:
						MedicoDao.mostrarMedicos();
						break;
					case 4:
						System.out.println("Dime el id del medico a eliminar");
						byte idMedicoEliminar = Byte.parseByte(sc.nextLine());
						
						MedicoDao.eliminarMedico(idMedicoEliminar);
						
						break;
					case 5:
						System.out.println("Volviendo al menu...");
						break;
					}
				} while (opcion2 < 1 || opcion2 > 5);

				break;

			case 3:
				byte opcion3;
				do {
					System.out.println();
					System.out.println("------ Menú Citas ------");
					System.out.println("1. Insertar cita");
					System.out.println("2. Actualizar cita");
					System.out.println("3. Mostrar lista de citas");
					System.out.println("4. Eliminar cita");
					System.out.println("5. Volver al menú principal");
					System.out.print("Seleccione una opción:\n");

					opcion3 = Byte.parseByte(sc.nextLine());

					switch (opcion3) {
					case 1:
						
						System.out.println("Id del cliente de la cita:");
						byte idCliente = Byte.parseByte(sc.nextLine());
						
						//Hacemos un select para añadir cliente a la cita
						Cliente clienteCita = CitaDao.selectCliente(idCliente);
						
						System.out.println("Id del medico de la cita:");
						byte idMedico = Byte.parseByte(sc.nextLine());
						
						//Hacemos un select para añadir medico a la cita
						Medico medicoCita = CitaDao.selectMedico(idMedico);
						
						System.out.println("Fecha de la cita (aaaa-mm-dd):");
						Date fecha = Date.valueOf(sc.nextLine());
						
						System.out.println("Hora de la cita (hh:mm:ss):");
						Time hora = Time.valueOf(sc.nextLine());
						
						Cita cita = new Cita(clienteCita, medicoCita, fecha, hora);
						
						CitaDao.agregarCita(cita);
						
						break;
					case 2:
						System.out.println("Dime el id de la cita a modificar");
						byte id = Byte.parseByte(sc.nextLine());
						
						System.out.println("Dime la fecha nueva (aaaa-mm-dd)");
						Date nuevaFecha = Date.valueOf(sc.nextLine());
						
						System.out.println("Dime la nueva hora (hh:mm:ss)");
						Time nuevaHora = Time.valueOf(sc.nextLine());
						
						CitaDao.modificarCita(id, nuevaFecha, nuevaHora);
						
						break;
					case 3:
						CitaDao.mostrarCitas();
						break;
					case 4:
						System.out.println("Dime el id de la cita a eliminar");
						byte idCitaEliminar = Byte.parseByte(sc.nextLine());
						
						CitaDao.eliminarCita(idCitaEliminar);
						
						break;
					case 5:
						System.out.println("Volviendo al menu...");
						break;
					}
				} while (opcion3 < 1 || opcion3 > 5);
				
				break;

			case 4:
				System.out.println("Adios...");

				break;
			}

		} while (opcion != 4);

	}

}
