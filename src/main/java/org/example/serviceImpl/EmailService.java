package org.example.serviceImpl;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;
@NoArgsConstructor
public class EmailService {
    private String username;
    private String password;

    private Properties prop;

    public EmailService(String host, int port, String username, String password) {
        prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);

        this.username = username;
        this.password = password;
    }

    public EmailService(String host, int port) {
        prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
    }

    public void send(String text, String account) {
        try {
            new EmailService("smtp.gmail.com", 25, "namvnucn1@gmail.com", "wujz afng izuw ldcp")
                    .sendMail(text, account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail(String text, String account) throws Exception {

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("namvnucn1@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(account));
        message.setSubject("Lấy lại mật khẩu");

        String msg = "Mật khẩu tạm thời của bạn là :" + text +". Vui lòng đổi mật khẩu sau khi vào lại!";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
