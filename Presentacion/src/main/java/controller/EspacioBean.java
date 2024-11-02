package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.Espacio;
import servicios.ServiciosEspaciosLogicaRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

@Named("espacioBean")
@ViewScoped
public class EspacioBean implements Serializable {

    private List<Espacio> espacios;
    private Espacio espacio = new Espacio();
    private  ServiciosEspaciosLogicaRemote serviciosLogica;

    // Lookup de capa de datos
    private ServiciosEspaciosLogicaRemote lookupRemoteEJB() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        // Dirección contenedor logica
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8180");

        // Credenciales
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "nicolas");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "Esponja123*");
        // Lookup EJB
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        // Clase EJB remota - Coincidir con los nombres de la capa de logica
        String namespace = "ejb:";
        String appName = "LogicaEAR";
        String moduleName = "ServiciosLogica";
        String distinctName = "";
        String beanName = "ServiciosEspaciosLogica";
        String viewClassName = ServiciosEspaciosLogicaRemote.class.getName();

        Context ctx = new InitialContext(jndiProperties);
        return (ServiciosEspaciosLogicaRemote) ctx.lookup(
                namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }

    public EspacioBean() {
        try {
            this.serviciosLogica = lookupRemoteEJB();
            cargarEspacios();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void cargarEspacios() {
        espacios = serviciosLogica.getAllEspaciosLogic();
    }

    public String agregarEspacio() {
        serviciosLogica.addEspacioLogic(espacio);
        cargarEspacios();
        espacio = new Espacio(); // Reset para nuevo espacio
        return null;
    }

    public String actualizarEspacio(Espacio espacio) {
        serviciosLogica.updateEspacioLogic(espacio);
        cargarEspacios();
        return null;
    }

    public String eliminarEspacio(int id) {
        serviciosLogica.deleteEspacioLogic(id);
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