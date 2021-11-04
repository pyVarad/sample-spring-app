package com.app.aeportal.domain;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "role")
public class Roles {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "role_sequence",
            strategy = SEQUENCE
    )
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

//    @ManyToMany(mappedBy = "roles")
//    private Collection<Users> users;

    public Roles() { }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public Collection<Users> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Collection<Users> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String toString() {
//        return "Roles{" +
//                "id=" + id +
//                ", roleName='" + roleName + '\'' +
//                ", users=" + users +
//                '}';
//    }
}
