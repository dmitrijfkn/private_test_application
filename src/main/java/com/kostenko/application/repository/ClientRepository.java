package com.kostenko.application.repository;

import com.kostenko.application.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Boolean existsByEmail(String email);

    Client findClientByEmail(String email);
}