package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.exceptions.BadRequestException;
import com.pfa.microserviceusers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.models.token.PasswordRestToken;
import com.pfa.microserviceusers.repository.ResetPasswordRepository;
import com.pfa.microserviceusers.requests.ResetPassword;
import com.pfa.microserviceusers.requests.ApiResponse;
import com.pfa.microserviceusers.requests.ResetPasswordRequest;
import com.pfa.microserviceusers.service.EmailSenderService;
import com.pfa.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping("/change")
    public String reset(Model model, @RequestParam("token")String resetToken)
    {
        PasswordRestToken token = resetPasswordRepository.findByResetToken(resetToken);
        System.out.println(token);

        if(token != null && (token.getExpirationDate().after(new Date())))
        {
            token.getUser().getEmail();
            ResetPasswordRequest resetPasswordRequest =new ResetPasswordRequest();
            resetPasswordRequest.setEmail(token.getUser().getEmail());
            model.addAttribute("resetObject", resetPasswordRequest);
            return "reset";
        }
        return "error";
    }

    @PostMapping(value="/update")
    public String updatePassword(@ModelAttribute("resetObject") ResetPasswordRequest resetPasswordRequest)
    {
        System.out.println(resetPasswordRequest.getEmail());
        User user=userService.findByEmail(resetPasswordRequest.getEmail());
        if(user==null)
        {
            throw new BadRequestException("This user not exists");
        }
        String password=resetPasswordRequest.getNewPassword();
        String repassword=resetPasswordRequest.getConfirmNewPassword();
        System.out.println(repassword);
        if(!password.equals(repassword))
        {
            return "invalid";
        }
        user.setPassword(password);
        userService.saveUser(user);
        return "success";
    }
}
