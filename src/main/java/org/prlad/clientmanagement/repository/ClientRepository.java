package org.prlad.clientmanagement.repository;

import org.prlad.clientmanagement.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT m FROM Client m WHERE m.user.id LIKE %:userid%")
    Optional<List<Client>> findByUserId(@Param("userid") Integer userid);
}
