package com.freesoft.task.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 36, columnDefinition = "varchar(36)",nullable = false )
    String name;
    @Column(length = 36, columnDefinition = "varchar(36)",nullable = false )
    String surname;
    @Column(length = 70, columnDefinition = "varchar(70)",nullable = false )
    String password;
    @Email
    @Column(length = 36, columnDefinition = "varchar(36)",nullable = false,unique = true )
    String email;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;

}
