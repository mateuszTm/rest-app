package com.empik.restapp.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name="user_requests",
        indexes = @Index(name = "idx_user_login", columnList = "login"))
public class UserRequests {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length=50)
    private String login;
    @Column(name="request_count", nullable=false)
    private Integer requestCount;
}
