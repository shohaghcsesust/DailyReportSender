import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.io.FileUtils;

public class SimpleSendEmail {
	private String host = "bd1.bjitgroup.com";
    
	private String from = "ahsan.kabir@bjitgroup.com";
    
    private String to = "monjur.morshed@bjitgroup.com";    
    private String cc = "mohammad.m.haque@valmetpartners.com,abdullah.alrahed@valmetpartners.com,javed.hasan@bjitgroup.com,homayun.kabir@bjitgroup.com,mehedi.masud@bjitgroup.com,sajidul.huq@bjitgroup.com,susanta.kumar@bjitgroup.com,mainuddin@bjitgroup.com";

	//test
//	private String to = from;
//	private String cc = from;
	
    private String project = "Valmet";
    private String name = "Md_Ahsan_Kabir";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    private String todaysDate = dateFormat.format(new Date());
    private String subject = "["+project+"] Daily_Report_"+name+"_"+todaysDate;
    
    private String fileLocation = "D:\\Daily Reports\\";
    private String fileName = "Daily_Report_PDM_"+name+"_"+dateFormat.format(new Date());
    private String fileExtension = ".pdf";
    
    private String filePath = fileLocation+fileName+fileExtension;
    
    private String messageBodyFileName = "ReportBody.txt";
    private String messageBodyFilePath = fileLocation+messageBodyFileName;
    
    public static void main(String[] args) {
    	SimpleSendEmail obj = new SimpleSendEmail();
    	obj.sendReport();
    }
    
    public void sendReport() {    	
        boolean sessionDebug = false;
        Properties props = System.getProperties();
        props.put("mail.host", host);
        props.put("mail.transport.protocol", "smtp");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(sessionDebug);
        try {
        	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            for(String t : to.split(",")){
                msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(t));
            }
            
            for(String t : cc.split(",")){
                msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(t));
            }
            
            msg.setSubject(subject);
            msg.setSentDate(new Date());
                        
            //attach report
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();
            
            DataSource source = new FileDataSource(filePath);
            
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            
            String messageText = FileUtils.readFileToString(new File(messageBodyFilePath), "UTF-8");
        	System.out.println("MessageText:\n"+messageText);
        	
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(messageText, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);
            
            msg.setContent(multipart);
            
            System.out.println("Sending...");
            Transport.send(msg);
            System.out.println("Done");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }
    }
}