# Taller EJBS Arquitectura de 3 Niveles

## Introducción
Este repositorio contiene un proyecto de arquitectura de 3 niveles basado en Java EE, que utiliza WildFly como servidor de aplicaciones y MySQL como base de datos. A continuación, se explican los pasos para configurar y ejecutar el proyecto.

## Requisitos Previos
- MySQL como base de datos.
- Servidor de aplicaciones WildFly 33.0.1.
- Archivos `.ear` y `.war` incluidos en el repositorio.

## Instrucciones para la Configuración y Ejecución

### 1. Creación de la Base de Datos
1. **Configurar la base de datos**: Se recomienda nombrar la base de datos como `ASMySQL` y utilizar las siguientes credenciales:
   - **Usuario**: `nicolas_r`
   - **Contraseña**: `Esponja123*`

2. **Crear el esquema**: Crear un esquema denominado `proyectoas`.

3. **Ejecutar el script DDL**: Utilizar el archivo DDL proporcionado en la carpeta del proyecto para crear la tabla `espacio`.

### 2. Configuración de los Contenedores WildFly
1. **Instalación de los contenedores**:
   - Instalar tres instancias de WildFly 33.0.1.

2. **Creación de usuarios**:
   - Añadir el mismo usuario a los tres contenedores ejecutando el siguiente comando:
     ```bash
     ./bin/add-user.bat
     ```
   - **Detalles del usuario**:
     - **Usuario**: `nicolas`
     - **Contraseña**: `Esponja123*`
     - **Roles**: `User, Administrator, guest`

3. **Configuración de los archivos de configuración**:
   - En cada contenedor, reemplazar el archivo `standalone.xml` correspondiente.
   - Para el contenedor de lógica, utilizar `standalone-full.xml`.
   - En el contenedor de datos, agregar la carpeta `mysql` en la ruta `modules/system/layers/base/com`.

### 3. Inicio de los Contenedores
- Iniciar cada contenedor ejecutando el comando:
  ```bash
  ./bin/standalone.bat
- Para el caso de lógica ejecutar:
  ```bash
  ./bin/standalone.bat -c standalone-full.xml
### 4. Despliegue de Aplicaciones
1. **Acceso a la consola de administración**:
   - Iniciar sesión en la consola de administración de WildFly usando las credenciales configuradas.

2. **Despliegue del archivo `DatosEAR.ear`**:
   - Subir el archivo `DatosEAR.ear` en la sección *Deployments* de la consola de administración de cada contenedor.

3. **Repetir el despliegue**:
   - Realizar el mismo proceso de despliegue para todos los contenedores, asegurándose de desplegar los archivos `.ear` y `.war` proporcionados.

### 5. Prueba de la Aplicación
- **Acceso a la aplicación**:
  - Abrir un navegador e ingresar la siguiente URL:
    ```text
    http://localhost:8080/Presentacion_war/index.xhtml
    ```

- **Verificación de la funcionalidad**:
  - Probar la gestión de espacios para confirmar que la aplicación está funcionando correctamente.
