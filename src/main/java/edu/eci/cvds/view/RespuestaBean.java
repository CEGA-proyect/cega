package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Respuesta;
import edu.eci.cvds.samples.services.ServicioRespuesta;
import edu.eci.cvds.samples.services.SolidaridadEscuelaException;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.time.LocalDate;
import java.util.Map;

@ManagedBean(name = "RespuestaBean")
@SessionScoped
public class RespuestaBean extends BasePageBean {
    @Inject
    ServicioRespuesta servicioRespuesta;

    private int id;
    private String nombre;
    private LocalDate fechaDeCreacion;
    private Integer oferta_id;
    private Integer necesidad_id;
    private String comentario;
    private String message = "";
    private String usuario_id = "1000950506";


    public void crearRespuesta() throws SolidaridadEscuelaException {
        try {
            fechaDeCreacion = LocalDate.now();
            if(necesidad_id == 0){
                necesidad_id = null;
            }
            if(oferta_id == 0){
                oferta_id = null;
            }
            Respuesta respuesta = new Respuesta(nombre,comentario,fechaDeCreacion,necesidad_id,oferta_id,usuario_id);
            servicioRespuesta.crearRespuesta(respuesta);
            message = "respuesta creada";
        } catch (Exception e) {
            message = "Error al crear la Oferta";
            throw new SolidaridadEscuelaException(e.getMessage());
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getNecesidad_id() {
        return necesidad_id;
    }
    public void setNecesidad_id(Integer necesidad_id) {
        this.necesidad_id = necesidad_id;
    }

    public void setOferta_id(Integer oferta_id) {
        this.oferta_id = oferta_id;
    }

    public Integer getOferta_id() {
        return oferta_id;
    }


}
