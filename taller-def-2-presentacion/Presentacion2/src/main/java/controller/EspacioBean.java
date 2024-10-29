package controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import jakarta.ejb.EJB;
import model.Espacio;
import servicios.ServiciosEspaciosLogicaRemote;

@ManagedBean(name = "espacioBean")
@RequestScoped
public class EspacioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    Controlador serviciosEspacios;

    private Espacio espacio = new Espacio();
    private List<Espacio> espacios;

    public EspacioBean() {
        cargarEspacios();
    }

    public void cargarEspacios() {
        espacios = serviciosEspacios.getAllEspaciosLogic();
    }

    public String agregarEspacio() {
        serviciosEspacios.addEspacioLogic(espacio);
        cargarEspacios();
        espacio = new Espacio(); // Reset para nuevo espacio
        return null;
    }

    public String actualizarEspacio(Espacio espacio) {
        serviciosEspacios.updateEspacioLogic(espacio);
        cargarEspacios();
        return null;
    }

    public String eliminarEspacio(int id) {
        serviciosEspacios.deleteEspacioLogic(id);
        cargarEspacios();
        return null;
    }

    // Getters y Setters

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public List<Espacio> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<Espacio> espacios) {
        this.espacios = espacios;
    }
}