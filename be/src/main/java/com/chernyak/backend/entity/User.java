package com.chernyak.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    @Size(min = 3, max = 20, message = "Username size must be between 3 and 20 symbols")
    @NotBlank(message = "Username is required")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @Size(min = 3, max = 20, message = "First name size must be between 3 and 20 symbols")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 20, message = "Last name size must be between 3 and 20 symbols")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Column(name = "email", unique = true)
    @Email(message = "Incorrect email format")
    @NotBlank(message = "Email is required")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prject_id")
    private Project project;

    //@Column(name = "lastSession")
    //private Date lastSession;
    @NotNull(message = "User role is required")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
