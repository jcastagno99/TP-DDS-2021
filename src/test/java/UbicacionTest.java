import domain.Mascotas.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UbicacionTest {
  private Ubicacion ubicacion1 = new Ubicacion (-10, 5);
  private Ubicacion ubicacion2 = new Ubicacion (2, 1);

  @Test
  public void seCalculaCorrectamenteLaDistanciaEntre2Ubicaciones() {
    assertEquals(12.649110640673518, ubicacion1.calcularDistanciaA(ubicacion2));
  }
}
