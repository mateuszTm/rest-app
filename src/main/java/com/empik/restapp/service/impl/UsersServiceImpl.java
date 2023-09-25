package com.empik.restapp.service.impl;

import com.empik.restapp.dto.User;
import com.empik.restapp.persistence.service.UserRequestsService;
import com.empik.restapp.restclient.github.GitHubUsersClient;
import com.empik.restapp.restclient.github.dto.GitHubUser;
import com.empik.restapp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final GitHubUsersClient gitHubUsersClient;
    private final UserRequestsService userRequestsService;

    @Override
    public User getUser(String login) {
        GitHubUser gitHubUser = gitHubUsersClient.getUser(login);
        userRequestsService.incrementAndSaveCallsCount(login);
        return convertToUser(gitHubUser);
    }

    private User convertToUser(GitHubUser gitHubUser) {
        return User.builder()
                .id(gitHubUser.getId())
                .login(gitHubUser.getLogin())
                .name(gitHubUser.getName())
                .type(gitHubUser.getType())
                .avatarUrl(gitHubUser.getAvatarUrl())
                .createdAt(gitHubUser.getCreatedAt())
                .calculations(getCalculations(gitHubUser.getFollowers(), gitHubUser.getPublicRepos()))
                .build();
    }

    private Double getCalculations(Integer followers, Integer publicRepos) {
        return (6.0 / followers) * (2.0 + publicRepos);
    }
}