package servicios;

import jakarta.ejb.Remote;

import model.Espacio;
import java.util.List;

@Remote
public interface ServiciosEspaciosLogicaRemote {
	
	
	public String addEspacioLogic(Espacio espacio);
	public List<Espacio> getAllEspaciosLogic();
	public List<Espacio> findEspacioLogic(int espacioId);
	public String updateEspacioLogic(Espacio espacio);
	public String deleteEspacioLogic(int espacioId);
	

}
