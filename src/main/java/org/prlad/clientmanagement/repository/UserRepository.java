package org.prlad.clientmanagement.repository;

import org.prlad.clientmanagement.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findById(String id);
    public Optional<User> findByUsername(String name);
}