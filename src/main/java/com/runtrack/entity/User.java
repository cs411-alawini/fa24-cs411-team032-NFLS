package com.runtrack.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@Table("User")
public class User {

    @Id
    @Column("UserId")
    private String userId;

    @Column("FirstName")
    private String firstName;

    @Column("LastName")
    private String lastName;

    @Column("Email")
    private String email;

    @Column("PhoneNumber")
    private String phoneNumber;

    @Column("Password")
    private String password;
}


