package domain.Asociacion;

import java.util.List;
import javax.persistence.*;

@Entity
public class Pregunta {
  private String pregunta;
  @ElementCollection
  private List<String> respuestasPosibles;
  private String respuesta;

  @Id
  @GeneratedValue
  private long id;

  public Pregunta(String pregunta, List<String> respuestasPosibles) {
    this.pregunta = pregunta;
    this.respuestasPosibles = respuestasPosibles;
  }

  public Pregunta(){}

  public void setRespuesta(String respuesta) {
    this.respuesta = respuesta;
    //Este metodo se llama cuando selecciona la respuesta dentro de la pool de respuestas posibles
  }

}
