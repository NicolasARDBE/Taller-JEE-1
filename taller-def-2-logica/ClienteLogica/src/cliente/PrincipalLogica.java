package cliente;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.Espacio;
import servicios.ServiciosEspaciosLogicaRemote;

public class PrincipalLogica {

    public static void main(String[] args) {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        
        // Ajustar IP de contenedor lógica
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8180");
        
        // Credenciales
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "nicolas");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "Esponja123*");
        
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        // Ajustar nombres de clases si es necesario
        Context ctx;
        String namespace = "ejb:";
        String appName = "LogicaEAR";
        String moduleName = "ServiciosLogica";
        String distinctName = "";
        String beanName = "ServiciosEspaciosLogica";
        String viewClassName = ServiciosEspaciosLogicaRemote.class.getName();
        ServiciosEspaciosLogicaRemote serviciosEspacios = null;

        try {
            ctx = new InitialContext(jndiProperties);
            serviciosEspacios = (ServiciosEspaciosLogicaRemote) ctx.lookup(
                    namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
        // Invocación del método
        List<Espacio> lista = serviciosEspacios.getAllEspaciosLogic();
        for (Espacio espacio : lista) {
            System.out.println(espacio.getId() + ", " + espacio.getNombre());
        }
        System.out.println("Size: " + lista.size());
    }
}