package servicios;

import java.util.List;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import model.Espacio;

/**
 * Session Bean implementation class ServiciosDatosEspacios
 */
@Stateless
@LocalBean
public class ServiciosDatosEspacios implements ServiciosDatosEspaciosRemote {
	
    @PersistenceContext(unitName = "EntidadesPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    /**
     * Default constructor. 
     */
    public ServiciosDatosEspacios() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addEspacio(Espacio espacio) {
		Espacio espacioAux = em.find(Espacio.class, espacio.getId());
		if (espacioAux == null) {
		em.persist(espacio);
		return "insertado";
		} else {
		return "existe";
		}
	}

	@Override
	public List<Espacio> findEspacio(int espacioId) {
	    String consulta = "SELECT e FROM Espacio e WHERE e.id = :espacioId";
	    TypedQuery<Espacio> query = em.createQuery(consulta, Espacio.class);
	    query.setParameter("espacioId", espacioId);
	    query.setMaxResults(1);
	    return query.getResultList();
	}

	@Override
	public List<Espacio> getAllEspacios() {
		return em.createQuery("SELECT e FROM Espacio e", Espacio.class).getResultList();
	}

	@Override
	public String updateEspacio(Espacio espacio) {
	    Espacio espacioExistente = em.find(Espacio.class, espacio.getId());
	    if (espacioExistente != null) {
	        // Actualiza los campos necesarios
	        espacioExistente.setNombre(espacio.getNombre());
	        espacioExistente.setCapacidad(espacio.getCapacidad());
	        em.merge(espacioExistente); // Sincroniza el estado del objeto con la base de datos
	        return "actualizado";
	    } else {
	        return "no existe";
	    }
	}

	@Override
	public String deleteEspacio(int espacioId) {
	    Espacio espacio = em.find(Espacio.class, espacioId);
	    if (espacio != null) {
	        em.remove(espacio); // Elimina el espacio
	        return "eliminado";
	    } else {
	        return "no existe";
	    }
	}

}
