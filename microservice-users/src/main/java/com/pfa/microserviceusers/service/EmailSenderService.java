package com.pfa.microserviceusers.service;

import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.token.ConfirmationToken;
import com.pfa.microserviceusers.models.token.PasswordRestToken;
import com.pfa.microserviceusers.repository.ConfirmationTokenRepository;
import com.pfa.microserviceusers.repository.ResetPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailSenderService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    @Value("${spring.mail.username}")
    private String emailSender;

    @Value("${server.port}")
    private String port;

    private final String server="localhost";

    private final Logger log = LoggerFactory.getLogger(ConfirmationToken.class);

    @Async
    public void sendConfirmationEmail(User user) {
        //log.info(user.getEmail());
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        //log.info(confirmationToken.getConfirmationToken());
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom(emailSender);
        mailMessage.setText("To confirm your account, please click here : "
                +"http://"+server+":"+port+"/confirmation/confirm-account?token="+confirmationToken.getConfirmationToken());
        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendResetPasswordEmail(User user) {
        //log.info(user.getEmail());
        PasswordRestToken passwordRestToken = new PasswordRestToken(user);
        //log.info(confirmationToken.getConfirmationToken());
        resetPasswordRepository.save(passwordRestToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reset password!");
        mailMessage.setFrom(emailSender);
        mailMessage.setText("To change your password, please click here : "
                +"http://"+server+":"+port+"/reset-password/change?token="+passwordRestToken.getResetToken());
        javaMailSender.send(mailMessage);
    }

}
