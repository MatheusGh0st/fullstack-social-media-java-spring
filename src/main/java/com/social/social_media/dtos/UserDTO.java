package com.social.social_media.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserDTO {
    private UUID idUser;
    private String name;
    private String avatar;
    private String description;
    private String surname;
    private String username;
    private String email;
    private String city;
    private String school;
    private String work;
    private String website;
    private List<PostDTO> posts;
    private LocalDateTime createdAt;

    // Getters and Setters
    public UserDTO() {}

    public UserDTO(UUID idUser, String name) {
        this.idUser = idUser;
        this.name = name;
    }

    public UserDTO(UUID idUser, String name, String avatar, String description, String surname, String username, String email, String city, String school, String work, String website, List<PostDTO> posts, LocalDateTime createdAt) {
        this.idUser = idUser;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.city = city;
        this.school = school;
        this.work = work;
        this.website = website;
        this.posts = posts;
        this.createdAt = createdAt;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
