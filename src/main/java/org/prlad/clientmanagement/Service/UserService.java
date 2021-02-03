package org.prlad.clientmanagement.Service;

import org.prlad.clientmanagement.pojo.User;
import org.prlad.clientmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public boolean isExisted(String name){
        return repository.findByUsername(name).isPresent();
    }

    public Optional<User> findByUsername(String username){
        return repository.findByUsername(username);
    }

    public Optional<User> createUser(User client) {
        return Optional.of(repository.save(client));
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findById(Integer id){
        return repository.findById(id);
    }

    public Boolean updateClient(User client){
        Optional<User> newclient = Optional.of(repository.save(client));
        if(newclient.isPresent()) return true;
        return false;
    }

}
