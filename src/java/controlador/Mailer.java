/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Aprendiz
 */
public class Mailer {
    Session session;
    //Credenciales de la cuenta de correo
    final String user = "santamartaflowers@gmail.com";//cambiará en consecuencia al servidor utilizado
    final String pass = "flowersx";

    public Mailer(Session session) {
        this.session = session;
    }

    public Mailer() {
    }

    public void configurar() {
        //Configurar el servidor de correo
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        //Obtener el objeto de sesión
        this.session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
    }

    public void enviarMensaje(String destinatario, String asunto, String mensaje) throws UnsupportedEncodingException {
        //Estructura del mensaje en HTML
        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#79B3AA; font-weight: bold; text-transform: uppercase ; \">FlowersX - Mensaje del administrador" + "</h1>" + "<img src=\"https://i.imgur.com/2seKBWE.png\"/ style=\"float: left;\"><p>" + mensaje + "<br>\n"
                + "<p style=\"text-align: center;\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#79B3AA;font-weight: bold;\" > Gracias por creer en nosotros. </p> ";
         try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");
            //Enviar mensaje
            Transport.send(message);
            System.out.println("Enviado");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarMensajeConUnAdjunto(String destinatario, String asunto, String mensaje, String ruta, String nombre) throws UnsupportedEncodingException {
        //Estructura del mensaje
        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">FlowersX - " + asunto + "</h1>" + "<img src=\"https://i.imgur.com/2seKBWE.png\"/ style=\"float: left;\"><br>\n"
                + "<p style=\"text-align: center; color: #222222\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por creer en nosotros. </p> ";
         try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            //Configurar adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nombre);
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(adjunto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void enviarMensajeConDosAdjuntos(String destinatario, String asunto, String mensaje, String ruta, String nombre, String ruta1, String nombre1) throws UnsupportedEncodingException {
        //Estructura del mensaje
        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">FlowersX - " + asunto + "</h1>" + "<img src=\"https://i.imgur.com/2seKBWE.png\"/ style=\"float: left;\"><br>\n"
                + "<p style=\"text-align: center; color: #222222\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por creer en nosotros. </p> ";
         try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            //Configurar adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nombre);
            BodyPart adjunto1 = new MimeBodyPart();
            adjunto1.setDataHandler(new DataHandler(new FileDataSource(ruta1)));
            adjunto1.setFileName(nombre1);
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(adjunto);
            multiparte.addBodyPart(adjunto1);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void enviarMensajeConTresAdjuntos(String destinatario, String asunto, String mensaje, String ruta, String nombre, String ruta1, String nombre1, String ruta2, String nombre2) throws UnsupportedEncodingException {
        //Estructura del mensaje
        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">FlowersX - " + asunto + "</h1>" + "<img src=\"https://i.imgur.com/2seKBWE.png\"/ style=\"float: left;\"><br>\n"
                + "<p style=\"text-align: center; color: #222222\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" > Gracias por creer en nosotros. </p> ";
         try {
            //Configurar mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(nuevoMensaje, "text/html");
            //Configurar adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nombre);
            BodyPart adjunto1 = new MimeBodyPart();
            adjunto1.setDataHandler(new DataHandler(new FileDataSource(ruta1)));
            adjunto1.setFileName(nombre1);
            BodyPart adjunto2 = new MimeBodyPart();
            adjunto2.setDataHandler(new DataHandler(new FileDataSource(ruta2)));
            adjunto2.setFileName(nombre2);
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            multiparte.addBodyPart(adjunto);
            multiparte.addBodyPart(adjunto1);
            multiparte.addBodyPart(adjunto2);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, ""));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
