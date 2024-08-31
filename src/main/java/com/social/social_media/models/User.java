package com.social.social_media.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String avatar;
    private String surname;
    private String username;
    private String email;
    private String description;
    private String city;
    private String school;
    private String work;
    private String website;
    private String password;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "followerId", cascade = CascadeType.ALL)
    private List<Follow> followers;

    @OneToMany(mappedBy = "followeedId", cascade = CascadeType.ALL)
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Storie> getStories() {
        return stories;
    }

    public void setStories(List<Storie> stories) {
        this.stories = stories;
    }

    public List<Block> getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(List<Block> blockedBy) {
        this.blockedBy = blockedBy;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<FollowRequest> getFollowRequestsReceived() {
        return followRequestsReceived;
    }

    public void setFollowRequestsReceived(List<FollowRequest> followRequestsReceived) {
        this.followRequestsReceived = followRequestsReceived;
    }

    public List<FollowRequest> getFollowRequestsSent() {
        return followRequestsSent;
    }

    public void setFollowRequestsSent(List<FollowRequest> followRequestsSent) {
        this.followRequestsSent = followRequestsSent;
    }

    public List<Follow> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Follow> followings) {
        this.followings = followings;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getIdUser() {
        return idUser;
    }


}