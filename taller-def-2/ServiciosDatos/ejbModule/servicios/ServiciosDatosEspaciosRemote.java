package servicios;

import java.util.List;

import jakarta.ejb.Remote;
import model.Espacio;

@Remote
public interface ServiciosDatosEspaciosRemote {
	
	public String addEspacio(Espacio espacio);
	public List<Espacio> findEspacio(int espacioId);
	public List<Espacio> getAllEspacios();
	public String updateEspacio(Espacio espacio);
	public String deleteEspacio(int espacioId);

}
