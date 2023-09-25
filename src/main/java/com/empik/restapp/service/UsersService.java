package com.empik.restapp.service;

import com.empik.restapp.dto.User;

public interface UsersService {
    User getUser(String login);
}
