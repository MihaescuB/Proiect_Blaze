package Utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;
import java.util.List;

public class EmailUtils {

    public static void sendEmail(String to, String subject, String body) {
        final String from = "applearnsql@gmail.com"; // Replace with your SMTP email
        final String password = "mskmqsnsomyzficg"; // Replace with your SMTP password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Use STARTTLS encryption
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Port for TLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Set the SSL protocol

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Sent email successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
        final String from = "applearnsql@gmail.com"; // Replace with your SMTP email
        final String password = "mskmqsnsomyzficg"; // Replace with your SMTP password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Use STARTTLS encryption
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Port for TLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Set the SSL protocol

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(new File(attachmentPath).getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent email with attachment successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailWithAttachments(String to, String subject, String body, List<String> attachmentPaths) {
        final String from = "applearnsql@gmail.com"; // Replace with your SMTP email
        final String password = "mskmqsnsomyzficg"; // Replace with your SMTP password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Use STARTTLS encryption
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Port for TLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Set the SSL protocol

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            // Attach multiple files
            for (String attachmentPath : attachmentPaths) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentPath);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(new File(attachmentPath).getName());
                multipart.addBodyPart(messageBodyPart);
            }

            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent email with attachments successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
