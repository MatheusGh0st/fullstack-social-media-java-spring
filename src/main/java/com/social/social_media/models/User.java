package com.social.social_media.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String description;
    private String city;
    private String school;
    private String work;
    private String website;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "followerId", cascade = CascadeType.ALL)
    private List<Follow> followers;

    @OneToMany(mappedBy = "followeeId", cascade = CascadeType.ALL)
    private List<Follow> followings;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<FollowRequest> followRequestsSent;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<FollowRequest> followRequestsReceived;

    @OneToMany(mappedBy = "blocker", cascade = CascadeType.ALL)
    private List<Block> blocks;

    @OneToMany(mappedBy = "blocked", cascade = CascadeType.ALL)
    private List<Block> blockedBy;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Storie> stories;

    @ManyToMany(fetch = FetchType.EAGER) // Fetch roles eagerly
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public UUID getIdUser() {
        return idUser;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}