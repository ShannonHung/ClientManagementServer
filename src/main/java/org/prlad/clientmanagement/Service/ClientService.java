package org.prlad.clientmanagement.Service;

import org.prlad.clientmanagement.pojo.Client;
import org.prlad.clientmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    public Optional<Client> createClient(Client client){
        return Optional.of(repository.save(client));
    }

    public Optional<List<Client>> findAll(){
        return Optional.of(repository.findAll());
    }
}
