package fachada;

import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Espacio;
import servicios.ServiciosDatosEspaciosRemote;


public class PrincipalDatos {

	public static void main(String[] args) {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "nicolas"); // Usuario de WildFly
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "Esponja123*"); // Contraseña de WildFly
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        Context ctx;
        String namespace = "ejb:";
        String appName = "DatosEAR"; // Nombre del proyecto EAR
        String moduleName = "ServiciosDatos"; // Nombre del proyecto de servicios
        String distinctName = "";
        String beanName = "ServiciosDatosEspacios"; // Nombre de la clase de servicios
        String viewClassName = ServiciosDatosEspaciosRemote.class.getName();
        ServiciosDatosEspaciosRemote serviciosEspacios = null;

        try {
            ctx = new InitialContext(jndiProperties);
            serviciosEspacios = (ServiciosDatosEspaciosRemote) ctx.lookup(
                    namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
        /*
        // Ejemplo de uso del método
        List<Espacio> lista = serviciosEspacios.getAllEspacios();
        for (Espacio espacio : lista) {
            System.out.println(espacio.getId() + ", " + espacio.getNombre());
        }
        System.out.println("Size: " + lista.size());
        */
        
        if(serviciosEspacios == null) {
        	System.out.println("F");
        } else {
        	System.out.println("Continuar...");
            Espacio e = new Espacio(2, "Cine y television updated", 300);
            System.out.println(serviciosEspacios.updateEspacio(e));
        }
	}
}
