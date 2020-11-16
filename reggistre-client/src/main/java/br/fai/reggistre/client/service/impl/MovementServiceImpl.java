package br.fai.reggistre.client.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.reggistre.client.service.MovementService;
import br.fai.reggistre.model.entities.Movimentacao;

@Service
public class MovementServiceImpl implements MovementService {

	@Override
	public List<Movimentacao> readAll() {

		List<Movimentacao> response = null;

		String endPoint = "http://localhost:8082/api/v1/movement/read-all";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			ResponseEntity<Movimentacao[]> requestResponse = restTemplate.exchange(endPoint, HttpMethod.GET,
					requestEntity, Movimentacao[].class);

			Movimentacao[] movement = requestResponse.getBody();
			response = Arrays.asList(movement);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(Movimentacao entity) {

		Long id = Long.valueOf(0);

		String endPoint = "http://localhost:8082/api/v1/movement/create";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<Movimentacao> requestEntity = new HttpEntity<Movimentacao>(entity);
			ResponseEntity<String> requestResponse = restTemplate.exchange(endPoint, HttpMethod.POST,
					requestEntity, String.class);

			String response = requestResponse.getBody();
			id = Long.parseLong(response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return id;
	}

	@Override
	public Movimentacao readById(Long id) {
		Movimentacao response = null;

		String endPoint = "http://localhost:8082/api/v1/movement/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			ResponseEntity<Movimentacao> requestResponse = restTemplate.exchange(endPoint, HttpMethod.GET,
					requestEntity, Movimentacao.class);

			response = requestResponse.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Movimentacao entity) {
		boolean response = false;
		
		String endPoint = "http://localhost:8082/api/v1/movement/update";

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<Movimentacao> requestEntity = new HttpEntity<Movimentacao>(entity);
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endPoint, HttpMethod.PUT,
					requestEntity, Boolean.class);

			 response = requestResponse.getBody();
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	@Override
	public boolean deleteById(Long id) {
		
		boolean response = false;

		String endPoint = "http://localhost:8082/api/v1/movement/delete/" + id;

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			ResponseEntity<Boolean> resquestResponse = restTemplate.exchange(endPoint, HttpMethod.DELETE,
					requestEntity, Boolean.class);

			response = resquestResponse.getBody();

		} catch (Exception e) {
			System.out.println("user service impl delete by id");
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public void notificarViaEmail(String email, String texto)
	{
      
      Properties props = new Properties();
      /**
       * Parâmetros de conexão com servidor Gmail
       */
      
      
//      /**/
//      props.put ("mail.smtp.host", "smtp.gmail.com");
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.debug", "true");
//      props.put("mail.smtp.debug", "true");
//      props.put("mail.mime.charset", "ISO-8859-1");
//      props.put("mail.smtp.port", "465");
//      props.put ("mail.smtp.starttls.enable", "true");
//      props.put ("mail.smtp.socketFactory.port", "465");
//      props.put ("mail.smtp.socketFactory.fallback", "false");
//      props.put ("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//      
//      /**/
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class",
              "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "465");
      

      Session session = Session.getDefaultInstance(props,
              new javax.mail.Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("avisos.reggistre@gmail.com", "ReggistreAvisos");
          }
      });
      session.setDebug(true);
      try {
          Message message = new MimeMessage(session);
          
          
          /*De quem*/
          message.setFrom(new InternetAddress("avisos.reggistre@gmail.com"));                                    
          /*Pra quem*/
          Address[] toUser = InternetAddress.parse(email);
          message.setRecipients(Message.RecipientType.TO, toUser);
          /*Assunto*/
          //message.setSubject("Notificação de mal uso do SafeWalk");
          message.setSubject("Email do Reggistre");
          /*Conteudo do email*/
          message.setContent(texto,"text/html");
          /**
           * 
           * Método para enviar a mensagem criada
           */                        
          Transport.send(message);
          
      } catch (MessagingException e) {
          e.getMessage();
          throw new RuntimeException(e);
          
      }


	}

//	@Override
//    public void notificarViaEmail(String emailReceber, String textoEnviar) throws Exception {
//        
//        Properties props = new Properties();
//        /**
//         * Parâmetros de conexão com servidor Gmail
//         */
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//        
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("recuperar.safewalk@gmail.com", "safewalktcc");
//            }
//        });
//        session.setDebug(true);
//        try {
//            Message message = new MimeMessage(session);
//            
//            
//            /*De quem*/
//            message.setFrom(new InternetAddress("recuperar.safewalk@gmail.com"));                                    
//            /*Pra quem*/
//            Address[] toUser = InternetAddress.parse(emailReceber);
//            message.setRecipients(Message.RecipientType.TO, toUser);
//            /*Assunto*/
//            //message.setSubject("Notificação de mal uso do SafeWalk");
//            message.setSubject("Testando email SafeWalk");
//            /*Conteudo do email*/
//            message.setContent(textoEnviar,"text/html");
//            /**
//             * 
//             * Método para enviar a mensagem criada
//             */                        
//            Transport.send(message);
//            
//        } catch (MessagingException e) {
//            e.getMessage();
//            throw new RuntimeException(e);
//            
//        }
//
//    }

}
