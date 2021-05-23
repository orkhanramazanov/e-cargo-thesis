package com.orkhan.web.out.ecargo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
  @Autowired
  JavaMailSender mailSender;


  
  public boolean sendMail(String to, String text, String subject) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message,true);
    try {
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(text,true);
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }
    mailSender.send(message);
    return true;
  }
}
