package com.kostenko.application.service;

import com.kostenko.application.dto.ClientDTO;
import com.kostenko.application.entity.Client;
import com.kostenko.application.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public void registerClient(ClientDTO clientDTO) throws IllegalArgumentException {
        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new IllegalArgumentException();
        }

        Client client = clientFromDTO(clientDTO);

        clientRepository.save(client);

        logger.info("User with email \"" + client.getEmail() + "\" succesfully registrated");
    }

    public boolean authenticateClient(ClientDTO clientDTO) {
        if (clientRepository.existsByEmail(clientDTO.getEmail())) {

            Client client = clientRepository.findClientByEmail(clientDTO.getEmail());

            return client.getPassword().equals(clientDTO.getPassword());
        }

        return false;
    }

    private Client clientFromDTO(ClientDTO clientDTO) {
        Client client = new Client();

        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());

        return client;
    }
}