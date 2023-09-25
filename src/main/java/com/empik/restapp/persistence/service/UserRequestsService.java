package com.empik.restapp.persistence.service;

public interface UserRequestsService {
    void incrementAndSaveCallsCount(String login);
}
