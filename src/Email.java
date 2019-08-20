import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	public static void SendEmail(String HTMLS) {

		InternetAddress cc[];
		InternetAddress bcc[];
		InternetAddress to[];

		String mailto = "gagandeepsinghqa@gmail.com";
		String from = "gagan.gill8831@gmail.com";
		List<String> bcclist = new ArrayList<String>();
		List<String> cclist = new ArrayList<String>();

		try {

			String host = "smtp.gmail.com";
			// Set the host smtp address
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.setProperty("mail.imap.ssl.enable", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			Authenticator auth = new Email().new SMTPAuthenticator();
			Session session = Session.getDefaultInstance(props, auth);

			session.setDebug(false);
			// create a message
			Message msg = new MimeMessage(session);

			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(from, "Infosys Test Results");
			msg.setFrom(addressFrom);
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(HTMLS, "text/html");
			multipart.addBodyPart(messageBodyPart);

			to = new InternetAddress[1];
			to[0] = new InternetAddress(mailto);

			cc = new InternetAddress[cclist.size()];
			for (int i = 0; i < cclist.size(); i++)
				cc[i] = new InternetAddress(cclist.get(i));

			bcc = new InternetAddress[bcclist.size()];
			for (int i = 0; i < bcclist.size(); i++)
				bcc[i] = new InternetAddress(bcclist.get(i));

			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setRecipients(Message.RecipientType.BCC, bcc);
			msg.setRecipients(Message.RecipientType.CC, cc);

			// Setting the Subject and Content Type
			msg.setContent(multipart);
			msg.setSubject("Infosys Test Cases on CHROME Browser");
			// msg.setText("<html><body>" + html + "</body></html>");//,);

			Transport.send(msg);
			System.out.println("sent");

			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = "gagan.gill8831@gmail.com";
			String password = "#Ga21ga6500n$";
			return new PasswordAuthentication(username, password);
		}
	}
}