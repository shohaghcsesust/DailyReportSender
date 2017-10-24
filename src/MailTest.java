import java.util.LinkedHashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailTest {

	public static void main(String[] args) throws Exception {
		String pwd="Arisha003";
		int totalLength = 125;
		int totalColumns = 3;
		char columnDivider = '|';
		RowProperties msgRow = new RowProperties(totalLength, totalColumns, columnDivider);
		LinkedHashMap<String, ColumnProperties> msgColValue = new LinkedHashMap<String, ColumnProperties>();
		StringBuilder resultBuilder=new StringBuilder();
		resultBuilder.append(MessageProperties.getBorderRow(msgRow)).append("\n");
		//add header
		ColumnProperties colP = new ColumnProperties(25, ' ', ColumnProperties.PADING_POS_CENTER);
		ColumnProperties colP1 = new ColumnProperties(10, ' ', ColumnProperties.PADING_POS_CENTER);
		ColumnProperties colP2 = new ColumnProperties(90, ' ', ColumnProperties.PADING_POS_CENTER);
		msgColValue.put("Company Name",colP);
		msgColValue.put("CMMI Level", colP1);
		msgColValue.put("Employee List", colP2);
		resultBuilder.append(new MessageProperties(msgColValue, msgRow).getMessageRow()).append(MessageProperties.getBorderRow(msgRow)).append("\n");
		colP.setPaddingPos(ColumnProperties.PADING_POS_RIGHT);
		colP2.setPaddingPos(ColumnProperties.PADING_POS_RIGHT);
		String msg="<b>List of Software engineer</b>"+"\n";
		msg+="1. shohagh\n";
		msg+="2. akib\n";
		msgColValue = new LinkedHashMap<String, ColumnProperties>();
		msgColValue.put("BJIT Ltd.", colP);
		msgColValue.put("CMMIDEV 5", colP1);
		msgColValue.put(msg, colP2);
		resultBuilder.append(new MessageProperties(msgColValue, msgRow).getMessageRow()).append(MessageProperties.getBorderRow(msgRow)).append("\n");
		System.out.println(resultBuilder.toString());
		
		// Recipient's email ID needs to be mentioned.
		String to = "ahsan.kabir@bjitgroup.com";

		// Sender's email ID needs to be mentioned
		String from = "ahsankabircse@gmail.com";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.user", "ahsankabircse@gmail.com");
		properties.setProperty("mail.password", pwd);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Send the actual HTML message, as big as you like
			message.setContent(resultBuilder.toString(), "text/html");

			// Send message
//			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
