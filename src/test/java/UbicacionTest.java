import domain.Asociacion.UbicacionDeDominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UbicacionTest {
  private UbicacionDeDominio ubicacion1 = new UbicacionDeDominio(-10, 5);
  private UbicacionDeDominio ubicacion2 = new UbicacionDeDominio(2, 1);

  @Test
  public void seCalculaCorrectamenteLaDistanciaEntre2Ubicaciones() {
    assertEquals(12.649110640673518, ubicacion1.calcularDistanciaA(ubicacion2));
  }
}
