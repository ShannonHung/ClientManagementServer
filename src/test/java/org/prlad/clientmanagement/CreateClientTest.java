package org.prlad.clientmanagement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.prlad.clientmanagement.Service.ClientService;
import org.prlad.clientmanagement.Service.UserService;
import org.prlad.clientmanagement.pojo.Client;
import org.prlad.clientmanagement.pojo.Role;
import org.prlad.clientmanagement.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@SpringBootTest(classes = ClientmanagementApplication.class)
public class CreateClientTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Test
    public void testCreateUser(){
        User admin = User.builder()
        .username("shannon3")
        .password("123")
        .role(Role.ADMIN)
        .build();

        userService.createUser(admin);

        Optional<User> retriveUser = userService.findByUsername(admin.getUsername());
        Assertions.assertEquals(retriveUser.get().getUsername(), admin.getUsername());
        Assertions.assertTrue(retriveUser.get().getId() > 0);
    }

    @Test
    public void testCreateClient(){
        Client client = Client.builder()
        .clientId("clientId001")
        .clientName("clientNameShannon")
        .address("addressxxxx")
        .privateKey(new byte[10])
        .role("role studetn")
        .secret("securet").build();

        clientService.createClient(client);

        Client retriveClient = clientService.findAll().get().get(0);

        Assertions.assertEquals(retriveClient.getClientId(), client.getClientId());
        Assertions.assertTrue(retriveClient.getId() > 0);
    }

    @Test
    public void testAddClientToUser(){

        User admin = User.builder()
                .username("shannon2")
                .password("123")
                .role(Role.ADMIN)
                .build();

        Client client = Client.builder()
                .clientId("clientId001")
                .clientName("clientNameShannon")
                .address("addressxxxx")
                .privateKey(new byte[10])
                .role("role studetn")
                .secret("securet").build();

        List<Client> clients = new LinkedList<>();
        clients.add(client);

        admin.setClientSet(clients);
        userService.createUser(admin);

        Optional<User> retriveUser = userService.findByUsername(admin.getUsername());

        Assertions.assertEquals(retriveUser.get().getUsername(), admin.getUsername());
        Assertions.assertTrue(retriveUser.get().getClientSet().stream().count() > 0);
    }

    @Test
    public void testGetClientByUserId(){
        Optional<User> user = userService.findByUsername("shannon");
        log.info("this is user " + user.get().getClientSet());


    }


}
