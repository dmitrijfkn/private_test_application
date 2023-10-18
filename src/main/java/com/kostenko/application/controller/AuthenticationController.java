package com.kostenko.application.controller;

import com.kostenko.application.dto.ClientDTO;
import com.kostenko.application.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
    private final ClientService clientService;

    @Autowired
    AuthenticationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/authentication")
    public String authenticationForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());

        return "authentication";
    }

    @PostMapping("/authentication")
    public String authenticationSubmit(@ModelAttribute ClientDTO clientDTO, Model model) {
        if (clientService.authenticateClient(clientDTO)) {
            return "authres";
        } else {
            model.addAttribute("clientDTO", clientDTO);
            model.addAttribute("error", "password is incorrect");
            return "authentication";
        }
    }

}