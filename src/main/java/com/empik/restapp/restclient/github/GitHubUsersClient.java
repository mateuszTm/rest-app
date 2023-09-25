package com.empik.restapp.restclient.github;

import com.empik.restapp.restclient.github.dto.GitHubUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GitHubUsersClient {
    private final static String RESOURCE_PATH = "/users";

    private final WebClient gitHubWebClient;

    public GitHubUsersClient(@Qualifier("gitHubWebClient") WebClient gitHubWebClient) {
        this.gitHubWebClient = gitHubWebClient;
    }

    public GitHubUser getUser(String login) {
        return gitHubWebClient.get().uri(RESOURCE_PATH + "/" + login).retrieve()
                .bodyToMono(GitHubUser.class).block();
    }
}
