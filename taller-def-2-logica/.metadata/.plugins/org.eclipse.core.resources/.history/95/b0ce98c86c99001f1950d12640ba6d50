package servicios;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import model.Espacio;

/**
 * Session Bean implementation class ServiciosUsuariosLogica
 */
@Stateless
@LocalBean
public class ServiciosEspaciosLogica implements ServiciosEspaciosLogicaRemote {

    private ServiciosDatosEspaciosRemote serviciosEspaciosRemote;

    // Default constructor: Llamar al lookup en el constructor
    public ServiciosEspaciosLogica() {
        try {
            this.serviciosEspaciosRemote = lookupRemoteEJB();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    // Lookup de capa de datos
    private ServiciosDatosEspaciosRemote lookupRemoteEJB() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        
        // Dirección contenedor datos
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8280");
        
        // Credenciales
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "nicolas");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "Esponja123*");
        
        // Lookup EJB
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        // Clase EJB remota - Coincidir con los nombres de la capa de datos
        String namespace = "ejb:";
        String appName = "DatosEAR";
        String moduleName = "ServiciosDatos";
        String distinctName = "";
        String beanName = "ServiciosEspaciosLogica";
        String viewClassName = ServiciosDatosEspaciosRemote.class.getName();
        
        Context ctx = new InitialContext(jndiProperties);
        return (ServiciosDatosEspaciosRemote) ctx.lookup(
                namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }
    
    // Métodos de la interfaz
	@Override
	public String addEspacioLogic(Espacio espacio) {
		return serviciosEspaciosRemote.addEspacio(espacio);
	}
	
    @Override
    public List<Espacio> getAllEspaciosLogic() {
        List<Espacio> espacios = serviciosEspaciosRemote.getAllEspacios();
        return espacios;
    }

	@Override
	public List<Espacio> findEspacioLogic(int espacioId) {
        List<Espacio> espacios = serviciosEspaciosRemote.findEspacio(espacioId);
        return espacios;
	}

	@Override
	public String updateEspacioLogic(Espacio espacio) {
		return serviciosEspaciosRemote.updateEspacio(espacio);
	}

	@Override
	public String deleteEspacioLogic(int espacioId) {
		return serviciosEspaciosRemote.deleteEspacio(espacioId);
	}
}
