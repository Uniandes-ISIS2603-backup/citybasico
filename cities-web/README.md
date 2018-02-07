# Tutorial de documentaci�n REST con JAX-RS Analyzer
A lo largo de este taller aprender� c�mo integrar la herramienta JAX-RS con su proyecto Maven para utilizar la documentaci�n de c�digo para producir una documentaci�n de API REST extensiva y legible.

## Vista general de los pasos
1. Actualizar Maven en su Netbeans si no lo ha hecho. [Tutorial Actualizaci�n Maven en Netbeans](mavennetbeans.md)
2. Documentar el c�digo con Javadoc
3. Agregar y configurar JAX-RS Analyzer
4. Generar la documentaci�n
5. Visualizarla en Swagger

### Actualizar Maven en su Netbeans
El plugin de Maven que se utilizar� para generar la documentaci�n necesita que se utilice una versi�n m�nima de Maven3.3.9. Si nunca ha actualizado su Maven es posible que est� utilizando la versi�n que viene con Netbeans (3.0.5). Para actualizarla debe seguir los pasos del [Tutorial Actualizaci�n Maven en Netbeans](mavennetbeans.md)

### Documentar el c�digo con Javadoc
Para producir una documentaci�n que sea de utilidad para su equipo de desarrollo es importante que documente bien los m�todos de los servicios REST para que, adem�s de la generaci�n autom�tica del plugin, haya una descripci�n clara que explique su API.

Javadoc permite la utilizaci�n de algunos tag�s de HTML para crear una documentaci�n m�s apropiada seg�n sus requerimientos. La sugerencia es utilizar un formato de la siguiente forma:

``` html
/**
     * <h1>(GET|POST|PUT|DELETE) /url/{parametros} : Nombre Servicio</h1>
     * <p>Cuerpo de peticion (Si tiene): JSON {@link (ClaseDTO)}</p>
     * 
     * <p>(Descripci�n del servicio, qu� hace, cu�ndo se usa)</p>
     * 
     * Codigos de respuesta<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">200 OK
     * (Qu� responde en 200 OK)</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">(C�digo de error)
     * (Qu� responde cuando hay error)</code> 
     * @param (Los par�metros del m�todo)
     * @param (Si recibe JSON) {@link (ClaseDTO)} (Descripci�n del par�metro)
     * @return JSON (Si responde un JSON) {@link (ClaseDTO)} - (Descripci�n de la respuesta)
     * @throws (Tipo de Exepci�n) {@link (ClaseExcepci�n)} - (Descripci�n del escenario de error)
     */
```

Este formato contiene lo necesario para que tanto la documentaci�n que se genera con Javadoc, como la que se genera con el plugin, sean claras y de utilidad para el equipo de desarrollo.

### Agregar y configurar JAX-RS Analyzer
JAX-RS es un plugin de Maven desarrollado por Sebastian Daschner que utiliza las anotaciones de JAX-RS y el Bytecode generado para generar una documentaci�n de los servicios Rest que utiliza una aplicaci�n.

> Si quiere conocer m�s de c�mo funciona Jax-Rs analyzer puede ver el video explicativo de su creador: [Create REST documentation with JAX-RS Analyzer - YouTube](https://youtu.be/TmG0Tnqv3gk)  

El plugin funciona desde Maven por lo que basta con configurar el Pom para que se descargue y se ejecute de manera autom�tica. Para hacerlo debe buscar su archivo pom.xml del proyecto del FrontEnd en la capa que ofrece los servicios Rest y agregar el siguiente c�digo en la secci�n de plugins:

```xml
<plugin>
	<groupId>com.sebastian-daschner</groupId>
  	<artifactId>jaxrs-analyzer-maven-plugin</artifactId>
  	<version>0.16</version>
  	<executions>
  		<execution>
      			<goals>
          			<goal>analyze-jaxrs</goal>
          		</goals>
      			<configuration>
          			<backend>swagger</backend>
 				<deployedDomain>localhost:8080</deployedDomain>
          		</configuration>
		</execution>
	</executions>
</plugin>
```

> Si no encuentra la secci�n de plugins dentro de su pom.xml puede agregarla al final del archivo de la siguiente forma:  

```xml
<project ...>
	<!--Tags anteriores-->
  	<build>
  		<plugins>
      			<!--Plugin de Jax-RS Analyzer-->
		</plugins>
	</build>
</plugin>
```

El plugin se puede configurar para elegir de qu� manera se genera la documentaci�n, se puede elegir un archivo de texto plano con un formato para que se entienda el API o un documento Ascii o un archivo de Swagger UI. En este tutorial utilizaremos Swagger para producir una documentaci�n que sea �til para el equipo de desarrollo.

> Swagger UI es una plataforma de visualizaci�n de documentos de API Rest bastante amigable y sencilla de mantener. Si quiere leer m�s sobre la plataforma puede hacerlo en la p�gina: [API Development Tools | Swagger UI | Swagger](https://swagger.io/swagger-ui/)  

### Generar la documentaci�n
En primer lugar puede generar la documentaci�n de Javadoc para verificar el formato y la claridad de su Javadoc.

> Si quiere hacer una prueba r�pida sobre una sola clase de su proyecto puede utilizar el comando de consola de javadoc:  
> `javac -d RutaDondeQuiereGuardarElJavaDoc RutaDelArchivo.java`  

Al generar el Javadoc usted deber�a tener un formato similar a este:
![](https://preview.ibb.co/ihz3MH/Screen_Shot_2018_02_06_at_4_11_47_PM.png)

Este Javadoc ya le permite entender la funcionalidad de su API REST y comunicar a su equipo de desarrollo la forma de utilizar los servicios que su proyecto expone. Sin embargo el plugin, al utilizar esta documentaci�n y la generaci�n que hace con el an�lisis de JAX-RS, crea un documento mucho m�s �til para su equipo.

El plugin de JAX-RS Analyzer est� integrado con el proceso de build, como se puede apreciar en la configuraci�n del POM. Lo que est� especificando es que cuando se haga build del proyecto se utilizar� la funci�n `<goal>` de `analyze-jaxrs` para generar la documentaci�n. Es decir que basta con hacer `Clean and Build` en Netbeans para generarla.

> Una vez que inicie el proceso de build es probable que vea unos errores en consola de tipo `Could not access static property, reason: ...` Puede ignorar estos errores que son generados por el plugin y est�n en proceso de ser corregidos tal como lo expresa el desarrollador en el Issue Tracker de Github. Sin embargo estos errores no afectan la generaci�n del archivo de documentaci�n por lo que puede ignorarlos.  

Cuando termine el build de su proyecto puede buscar el archivo `swagger.json` en la carpeta `target` de su proyecto. Dentro de la carpeta de su proyecto front deber� buscar `target > jaxrs-analyzer > swagger.json`

![](https://preview.ibb.co/mW1t1H/Screen_Shot_2018_02_04_at_8_03_49_PM.png)

### Visualizarla en Swagger

Finalmente la documentaci�n est� lista para ser visualizada en Swagger UI. Lo primero que debe hacer es registrarse en la plataforma para poder crear el proyecto de su equipo dentro de la misma.

Para registrarse vaya a este link: [Build, Collaborate & Integrate APIs | SwaggerHub](https://app.swaggerhub.com/signup?utm_source=swaggerio&utm_medium=download-ui&utm_campaign=swaggerhub&_ga=2.121288456.1497930554.1517791522-1302079190.1515022654)

![](https://preview.ibb.co/iMaxTx/Screen_Shot_2018_02_04_at_8_06_32_PM.png)

> Puede iniciar sesi�n con su Github para mayor facilidad  

Siga las instrucciones de la p�gina para crear su organizaci�n (su equipo de desarrollo). Luego podr� invitar a sus compa�eros para que todos est�n dentro del mismo proyecto.

Una vez complete el registro le aparecer� una pantalla en la que podr� crear un nuevo API:

![](https://preview.ibb.co/ifccTx/Screen_Shot_2018_02_04_at_8_10_05_PM.png)

Seleccione la opci�n de Importar un API para que pueda subir el archivo `swagger.json` que se gener� con el plugin. Puede elegir darle un nombre a su API y la versi�n en la que se encuentra, o puede dejarlo con los valores por defecto.

Una vez finalice la importaci�n en Swagger usted estar� en una pantalla como esta:
![](https://preview.ibb.co/na0XFc/Screen_Shot_2018_02_06_at_4_16_06_PM.png)

Como puede ver Swagger le permite ver de forma muy clara c�mo es su API Rest, qu� servicios expone, cu�les son los par�metros que recibe, la respuesta y los c�digos de respuesta. Explore la interfaz para que se familiarice con ella.

Al abrir el detalle de alguno de los m�todos deber�a ver algo similar a esto:

![](https://preview.ibb.co/eVrHgH/Screen_Shot_2018_02_06_at_4_15_38_PM.png)

> Algunas funcionalidades de Swagger le permiten crear versiones de su API por ejemplo para permitirle crear una actualizaci�n en la que cambie algunos de los servicios pero que siga existiendo la documentaci�n para versiones antiguas. Le permite compartir su API si desea hacerla p�blica o incluso le genera el c�digo base para comunicarse con su API en distintos lenguajes como Angular, AngularJS, Android, Swift, Php y muchos otros.  

Por el momento el plugin no ser� capaz de entender sus c�digos de respuesta en caso de error puesto que no hay implementaci�n de l�gica dentro de sus servicios. A medida que avance en el desarrollo del proyecto podr� ver c�mo la informaci�n que extrae el plugin se vuelve m�s precisa.

El resultado final es que su equipo podr� compartir la documentaci�n de una forma clara y con funcionalidades �tiles para el desarrollo y la organizaci�n de su software.
