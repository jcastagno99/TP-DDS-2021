package domain.Mail;

//Esta clase implementaria la interfaz javamail

public class MailSender {

  private static final MailSender INSTANCE = new MailSender();

  public static MailSender instance() {
    return INSTANCE;
  }

  public void sendMail(Mail mail, String direccionMail) {
  }
}
