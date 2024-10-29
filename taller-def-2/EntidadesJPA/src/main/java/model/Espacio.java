package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the espacio database table.
 * 
 */
@Entity
@NamedQuery(name="Espacio.findAll", query="SELECT e FROM Espacio e")
public class Espacio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int capacidad;
	private String nombre;

	public Espacio() {
	}
	
	public Espacio(int id, String nombre, int capacidad) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}