package com.empik.restapp.service

import com.empik.restapp.persistence.service.UserRequestsService
import com.empik.restapp.restclient.github.GitHubUsersClient
import com.empik.restapp.restclient.github.dto.GitHubUser
import com.empik.restapp.service.impl.UsersServiceImpl
import spock.lang.Specification

import java.time.LocalDateTime

class UsersServiceTest extends Specification {
    UsersService usersService
    GitHubUsersClient gitHubUsersClient = Mock()
    UserRequestsService userRequestsService = Mock()

    def setup() {
        usersService = new UsersServiceImpl(gitHubUsersClient, userRequestsService)
    }

    def "should save call incrementAndSaveCallsCount and return user" (){
        given:
        1 * gitHubUsersClient.getUser("dummy_login") >> new GitHubUser(
                id: 1,
                login: "dummy_login",
                name: "dummy_name",
                type: "dummy_type",
                avatarUrl: "dummy_avatarUrl",
                followers: 10541,
                createdAt: LocalDateTime.parse("2015-05-30T18:54:23"),
                publicRepos: 8
        )

        when:
        def result = usersService.getUser("dummy_login")

        then:

        noExceptionThrown()
        1 * userRequestsService.incrementAndSaveCallsCount("dummy_login")
        with(result) {
            id              == 1
            login           == "dummy_login"
            name            == "dummy_name"
            type            == "dummy_type"
            avatarUrl       == "dummy_avatarUrl"
            createdAt       == LocalDateTime.parse("2015-05-30T18:54:23")
            calculations    == 0.005692059576890238
        }
    }
}
