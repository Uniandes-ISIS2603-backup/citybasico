/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
CITYS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.cities.resources;


import co.edu.uniandes.csw.cities.dtos.CityDetailDTO;
import co.edu.uniandes.csw.cities.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.cities.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso "cities".
 * URL: /api/cities
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "cities".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author ISIS2603  
 * @version %I% 
 */
@Path("cities")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CityResource {

    /**
     * <h1>POST /api/cities : Crear ciudad</h1>
     * <p>Cuerpo de peticion: JSON {@link CityDetailDTO}</p>
     * 
     * <p>Crea una nueva ciudad con la informacion que se recibe en el cuerpo de la peticion.</p>
     * 
     * Codigos de respuesta<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">200 OK
     * Se crea la nueva ciudad y se regresa un objeto identico con un id auto-generado por la base de datos.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">412 Precodition Failed
     * Ya existe la ciudad.</code>
     * @param city {@link CityDetailDTO} - La ciudad que se desea guardar.
     * @return JSON {@link CityDetailDTO}  - La ciudad guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de logica que se genera al no poder crear la ciudad.
     */
    @POST
    public CityDetailDTO createCity(CityDetailDTO city) throws BusinessLogicException {
        return city;
    }

    /**
     * <h1>GET /api/cities : Obtener ciudades</h1><br><br>
     * 
     * Busca y devuelve todas las ciudades que existen en la aplicacion.<br><br>
     * 
     * Codigos de respuesta<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">200 OK</code> 
     * Devuelve todas las ciudades de la aplicacion.<br>
     * <code style="color: #c7254e; background-color: #f9f2f4;">412 Precodition Failed</code> 
     * No se pudo acceder a la base de datos de ciudades.
     * @return JSONArray&lt;{@link CityDetailDTO}&gt; - Las ciudades encontradas en la aplicacion.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de logica que se genera al no poder acceder a las ciudades.
     */
    @GET
    public List<CityDetailDTO> getCities() throws BusinessLogicException {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/cities/{id} : Obtener ciudad por id</h1><br><br>
     * 
     * Busca la ciudad con el ID asociado recibido en la URL y la devuelve<br><br>
     * 
     * Codigos de respuesta<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">200 OK</code> 
     * Devuelve la ciudad buscada.<br>
     * <code style="color: #c7254e; background-color: #f9f2f4;">404 Not Found</code> 
     * No se pudo acceder a la base de datos de ciudades, o no existe la ciudad buscada.
     * @param id Identificador de la ciudad que se esta buscando
     * @return JSON&lt;{@link CityDetailDTO}&gt; - La ciudad buscada
     */
    @GET
    @Path("{id: \\d+}")
    public CityDetailDTO getCity(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/cities/{id} : Actualizar ciudad</h1><br><br>
     * Cuerpo de peticion: JSON&lt;{@link CityDetailDTO}&gt;<br><br>
     * 
     * Actualiza la ciudad con el ID recibido en la URL con la informacion que se recibe en el cuerpo de la peticion.<br><br>
     * 
     * Codigos de respuesta<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">200 OK</code> 
     * Se actualiza la ciudad y se regresa un objeto identico.<br>
     * <code style="color: #c7254e; background-color: #f9f2f4;">412 Precodition Failed</code> 
     * No existe la ciudad o no fue posible actualizarla en la base de datos.
     * @param id Identificador de la ciudad que se desea actualizar.
     * @param city {@link CityDetailDTO} La ciudad que se desea guardar.
     * @return JSON&lt;{@link CityDetailDTO}&gt; - La ciudad guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de logica que se genera al no poder actualizar la ciudad.
     */
    @PUT
    @Path("{id: \\d+}")
    public CityDetailDTO updateCity(@PathParam("id") Long id, CityDetailDTO city) throws BusinessLogicException {
        return city;
    }
    
    /**
     * <h1>DELETE /api/cities/{id} : Borrar ciudad por id</h1><br><br>
     * 
     * Borra la ciudad con el ID asociado recibido en la URL.<br><br>
     * 
     * Codigos de respuesta<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">200 OK</code>
     * Se elimino la ciudad.<br>
     * <code style="color: #c7254e; background-color: #f9f2f4;">404 Not Found</code>
     * No se pudo acceder a la base de datos de ciudades, o no existe la ciudad buscada.
     * @param id Identificador de la ciudad que se desea borrar
     * @throws BusinessLogicException - {@link BusinessLogicExceptionMapper} Error de logica cuando no se encuentra la ciudad.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteCity(@PathParam("id") Long id) throws BusinessLogicException {
        // Void
    }
}
