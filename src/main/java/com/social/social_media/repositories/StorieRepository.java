package com.social.social_media.repositories;

import com.social.social_media.dtos.StorieWithUserDTO;
import com.social.social_media.models.Storie;
import com.social.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface StorieRepository extends JpaRepository<Storie, UUID> {
    @Query("SELECT new com.social.social_media.dtos.StorieWithUserDTO(s.idStorie, s.content, s.expiresAt, " +
            "new com.social.social_media.dtos.UserDTO(u.idUser, u.name, u.avatar, u.description, u.surname, u.username, u.email, u.city, u.school, u.work, u.website, null, u.createdAt)) " +
            "FROM Storie s " +
            "JOIN s.userId u " +
            "WHERE s.expiresAt > :currentDate " +
            "AND (u IN (SELECT f.followerId FROM Follow f WHERE f.followeedId = :currentUser) " +
            "OR u = :currentUser)")
    List<StorieWithUserDTO> findStoriesByDateOrUserId(@Param("currentDate") LocalDateTime currentDate, @Param("currentUser") User currentUser);
}