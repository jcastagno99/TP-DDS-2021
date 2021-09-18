package domain.Notificadores.Mail;

public class Mail {

  private String asunto;
  private String cuerpo;
  private String emisor;

  public Mail(String asunto, String cuerpo, String emisor) {
    this.asunto = asunto;
    this.cuerpo = cuerpo;
    this.emisor = emisor;
  }
}
