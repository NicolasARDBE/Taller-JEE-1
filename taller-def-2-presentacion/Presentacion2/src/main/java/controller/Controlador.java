package controller;

import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import model.Espacio;
import servicios.ServiciosEspaciosLogicaRemote;

@Stateless
@LocalBean
public class Controlador implements ServiciosEspaciosLogicaRemote {
	
	private ServiciosEspaciosLogicaRemote serviciosLogica;
	
    public Controlador() {
        try {
            this.serviciosLogica = lookupRemoteEJB();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    // Lookup de capa de datos
    private ServiciosEspaciosLogicaRemote lookupRemoteEJB() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        
        // Dirección contenedor datos
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8180");
        
        // Credenciales
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "nicolas");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "Esponja123*");
        
        // Lookup EJB
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        // Clase EJB remota - Coincidir con los nombres de la capa de datos
        String namespace = "ejb:";
        String appName = "LogicaEAR";
        String moduleName = "ServiciosLogica";
        String distinctName = "";
        String beanName = "ServiciosDatosEspacios";
        String viewClassName = ServiciosEspaciosLogicaRemote.class.getName();
        
        Context ctx = new InitialContext(jndiProperties);
        return (ServiciosEspaciosLogicaRemote) ctx.lookup(
                namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }

    private int id;
    private String nombre;
    private int capacidad;

    // Getters y Setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    // Métodos de la interfaz
	@Override
	public String addEspacioLogic(Espacio espacio) {
		return serviciosLogica.addEspacioLogic(espacio);
	}
	
    @Override
    public List<Espacio> getAllEspaciosLogic() {
        List<Espacio> espacios = serviciosLogica.getAllEspaciosLogic();
        return espacios;
    }

	@Override
	public List<Espacio> findEspacioLogic(int espacioId) {
        List<Espacio> espacios = serviciosLogica.findEspacioLogic(espacioId);
        return espacios;
	}

	@Override
	public String updateEspacioLogic(Espacio espacio) {
		return serviciosLogica.updateEspacioLogic(espacio);
	}

	@Override
	public String deleteEspacioLogic(int espacioId) {
		return serviciosLogica.deleteEspacioLogic(espacioId);
	}
}