package domain.Asociacion;

import java.util.List;

public class Pregunta {
  private String pregunta;
  private List<String> respuestasPosibles;
  private String respuesta;

  public Pregunta(String pregunta, List<String> respuestasPosibles) {
    this.pregunta = pregunta;
    this.respuestasPosibles = respuestasPosibles;
  }

  public void setRespuesta(String respuesta) {
    this.respuesta = respuesta;
    //Este metodo se llama cuando selecciona la respuesta dentro de la pool de respuestas posibles
  }
}
