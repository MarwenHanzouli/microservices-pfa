package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.token.ConfirmationToken;
import com.pfa.microserviceusers.repository.ConfirmationTokenRepository;
import com.pfa.microserviceusers.service.EmailSenderService;
import com.pfa.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationEmailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        //log.info(token.getConfirmationToken());
        if(token != null)
        {
            User user = userService.findByUsernameOrEmail(token.getUser().getEmail(),token.getUser().getEmail());
            //log.info(user.getEmail());
            user.setEnabled(true);
            userService.updateUser(user);
            return "Congratulations! Your account has been activated and email is verified!";
        }
        return "The link is invalid or broken!";
    }
}
