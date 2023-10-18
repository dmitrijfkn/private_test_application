package com.kostenko.application.controller;

import com.kostenko.application.dto.ClientDTO;
import com.kostenko.application.service.ClientService;
import jakarta.validation.Valid;
//import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final ClientService clientService;

    @Autowired
    RegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());

        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute @Valid ClientDTO clientDTO, BindingResult bindingResult, Model model) {

        model.addAttribute("error","");

        if (bindingResult.hasErrors()) {
            model.addAttribute("clientDTO",clientDTO);
            return "registration";
        }

        try {
            clientService.registerClient(clientDTO);
        } catch (IllegalArgumentException iae) {
            model.addAttribute("error", "User with this email already registered");
            return "registration";
        } catch (Exception ex) {
            return "error";
        }

        model.addAttribute("clientDTO",clientDTO);
        return "regres";
    }

}