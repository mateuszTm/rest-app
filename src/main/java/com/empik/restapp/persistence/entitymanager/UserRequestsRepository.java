package com.empik.restapp.persistence.entitymanager;

import com.empik.restapp.persistence.entities.UserRequests;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRequestsRepository extends CrudRepository<UserRequests, Long> {
    Optional<UserRequests> findByLogin(String login);

    @Modifying
    @Query("UPDATE UserRequests u SET u.requestCount = u.requestCount + 1 WHERE u.login = :login")
    Integer incrementCallsCount(String login);
}
