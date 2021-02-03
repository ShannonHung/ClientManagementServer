package org.prlad.clientmanagement.config;


import org.prlad.clientmanagement.Service.ClientService;
import org.prlad.clientmanagement.Service.UserService;
import org.prlad.clientmanagement.pojo.Client;
import org.prlad.clientmanagement.pojo.Role;
import org.prlad.clientmanagement.pojo.User;
import org.prlad.clientmanagement.repository.ClientRepository;
import org.prlad.clientmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultDataConfig implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //建立user
        User admin = User.builder()
                .username("shannon")
                .password("$2a$10$4L2.8B0woZMR2oWvW7MM8OO6OcB3k3MYOkpMFcg0HZm9GLV67UypS") //123
                .role(Role.MEMBER)
                .build();

        //建立user的client
        Client client = Client.builder()
                .clientId("clientId001")
                .clientName("clientNameShannon")
                .address("addressxxxx")
                .privateKey(new byte[10])
                .role("role studetn")
                .secret("securet").build();

        List<Client> clients = new LinkedList<>();

        clients.add(client);
        //告訴admin有哪些client才可以把關連串起來
        admin.setClientSet(clients);
        //告訴Client你的user是誰
        client.setUser(admin);

        //存user進去
        userService.createUser(admin);

        Optional<User> retriveUser = userService.findByUsername(admin.getUsername());

    }
}
