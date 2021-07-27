package com.phase3.stockone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3.stockone.dao.UsersAppRepository;
import com.phase3.stockone.entities.UsersApp;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;


@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UsersAppRepository userRepo;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	// Add User using confirm
//	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/setuserapi", method = RequestMethod.POST)
	public String Stringreactuserapi(@RequestBody UsersApp user) throws AddressException, MessagingException, IOException {
		UsersApp user1= userDetailsService.save(user);
		
//		UsersApp usrsaved = userRepo.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "UserController");
		headers.add("Access-Control-Allow-Origin", "*");
		sendemail(user1.getId());
		return user1.toString();

	}

	// EMAIL...
	public void sendemail(Long userid) throws AddressException, MessagingException,IOException {
		
		UsersApp user = userRepo.getById(userid);
		Email from = new Email("gpathak161999@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email(user.getEmail());
	    Content content = new Content("text/html", "<h1><a href =\"https://stockoneapp-react.herokuapp.com/confirmuser/" + userid + "/\"> Click to confirm </a></h1>");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(System.getenv("SD_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (Exception ex) {
	      throw ex;
	    }

//		UsersApp user = userRepo.getById(userid);
//
//		final String username = "ayushtb7@gmail.com";
//		final String password = "springboot7A#";
//
//		Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//		prop.put("mail.smtp.port", "465");
//		prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.starttls.enable", "true");
//		prop.put("mail.smtp.starttls.required", "true");
//		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // TLS
//
//		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//				return new javax.mail.PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("sftrainerram@gmail.com"));
//			// message.setRecipients(
//			// Message.RecipientType.TO,
//			// InternetAddress.parse("sftrainerram@gmail.com")
//			// );
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
//			message.setSubject("USer confirmation email");
//			// message.setText("Dear Mail Crawler,"
//			// + "\n\n Please do not spam my email!");
//			message.setContent(
//					"<h1><a href =\"http://127.0.0.1:8080/confirmuser/" + userid + "/\"> Click to confirm </a></h1>",
//					"text/html");
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
	}

	// CONFIRMING...
	@RequestMapping(value = "/confirmuser/{userid}", method = RequestMethod.GET)
	public String welcomepage(@PathVariable Long userid) {
		Optional<UsersApp> userlist = userRepo.findById(userid);
		// do a null check for home work
		UsersApp usr = new UsersApp();
		usr = userRepo.getById(userid);
		usr.setConfirmed(true);
		userRepo.save(usr);
		return "User confirmed" + usr.getName();
	}
	
	// HAVE TO DELETE THIS MAPPING
	@PostMapping("/addUser1")
	public int addUserDetails(@RequestBody UsersApp user) {
		userRepo.save(user);
		return 101;
	}
	
	// GET ALL USERS
	@GetMapping("/getUsers")
	public List<UsersApp> getUserDetails() {
		return userRepo.findAll();
	}
	
	// HAVE TO DELETE THIS ENDPOINT
	@PostMapping("/getUserByName")
	public ResponseEntity<UsersApp> getUserDetailsByName(@RequestBody Map<String,String> userName) {
//		System.out.println(userName);
		UsersApp usersApp= userRepo.findByName(userName.get("userName"));
		
		return new ResponseEntity<>(usersApp,HttpStatus.OK);
	}
	
	// LOGIN ENDPOINT
	@PostMapping("/getUserByNameAndPass")
	public Object getUserDetailsByNameAndPass(@RequestBody Map<String, String> object) {
		
		Map<String, String> map=new HashMap<String, String>();
		if(userRepo.existsByNameAndPassword(object.get("name"),object.get("password"))){
			UsersApp usersApp =  userRepo.findByNameAndPassword(object.get("name"),object.get("password"));
			if(usersApp.getConfirmed()==true) {
				return usersApp;
			} else if(usersApp.getConfirmed()==false) {
				map.put("confirm", "Confirmation Mail was not opened");
				return map;
			}
		} else {
			map.put("response","No-user Found");
			return map;
		}
		return map;
	}
	
	@PutMapping("/updateUser")
	public int updateUserDetails(@RequestBody UsersApp user) {
		
		UsersApp newUser = new UsersApp();
		//newUser.setname(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	    newUser.setName(user.getName());
//	    newUser.setPassword(user.getPassword());
	    newUser.setEmail(user.getEmail());
		newUser.setRole("admin");
		newUser.setMobileNum(user.getMobileNum());
		newUser.setAdmin(user.getAdmin());
		newUser.setConfirmed(user.getConfirmed());
		newUser.setId(user.getId());
		userRepo.save(newUser);
		
		userRepo.save(user);
		return 202;
	}

}
