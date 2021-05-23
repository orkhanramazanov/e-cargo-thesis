package com.orkhan.web.out.ecargo.service;


import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTests {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @Test
    public void sendEmail() throws MessagingException {
        MailService  mailService  = mock(MailService.class);
        when(mailService.sendMail("d","d","d")).thenReturn(true);
        Boolean mail = mailService.sendMail("d","d","d");
        assertEquals(true,mail);
    }
}
