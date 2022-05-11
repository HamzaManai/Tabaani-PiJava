/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HPOMEN-I7-1TR
 */
public class JavaMail {

    /*
        public static void  sendMail(String recepient) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "stmp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String Email = "mhamza1506@gmail.com";
        String pwd = "Mmhm15069";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Email, pwd);
            }
                
        });

        Message message = prepareMessage(session, Email, recepient);

        Transport.send(message);
        System.out.println("Email Sent");
    }

    private static Message prepareMessage(Session session, String Email, String recepient) {
            
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(Email));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
                message.setSubject("Reservation");
                message.setText("you have made a reservation");
                return message;
            } catch ( Exception ex) {
                Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;

    }
     */
    public void mail() {

        // Recipient's email ID needs to be mentioned.
        String to = "manai.hamza@esprit.tn";

        // Sender's email ID needs to be mentioned
        String from = "sporttech007@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Reservation");

            // Now set the actual message
            message.setText("you have made a reservations");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
