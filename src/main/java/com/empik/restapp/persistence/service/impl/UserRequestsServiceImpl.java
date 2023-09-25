package com.empik.restapp.persistence.service.impl;

import com.empik.restapp.persistence.entities.UserRequests;
import com.empik.restapp.persistence.entitymanager.UserRequestsRepository;
import com.empik.restapp.persistence.service.UserRequestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRequestsServiceImpl implements UserRequestsService {
    private final UserRequestsRepository userRequestsRepository;

    @Transactional
    @Override
    public void incrementAndSaveCallsCount(String login) {
        Optional<UserRequests> userRequestsoptional = userRequestsRepository.findByLogin(login);
        UserRequests userRequests;
        if (userRequestsoptional.isPresent()) {
            userRequests = userRequestsoptional.get();
            userRequests.setRequestCount(userRequests.getRequestCount() + 1);
        } else {
            userRequests = new UserRequests();
            userRequests.setLogin(login);
            userRequests.setRequestCount(1);
        }
        userRequestsRepository.save(userRequests);
    }
}
