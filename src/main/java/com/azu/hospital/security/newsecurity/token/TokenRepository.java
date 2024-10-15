package com.azu.hospital.security.newsecurity.token;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TokenRepository extends JpaRepository<Token, Long> {


    @Query("""
    SELECT t FROM Token t
    LEFT JOIN User u ON t.users.id = u.id
    LEFT JOIN Nurse n ON t.users.id = n.id
    LEFT JOIN Doctor d ON t.users.id = d.id
    LEFT JOIN Permanent p ON t.users.id = p.id
    WHERE (u.id = :userId OR n.id = :userId OR d.id = :userId OR p.id = :userId)
    
""")
    List<Token> findAllInvalidToken(@Param("userId") Long userId);




    @Query("SELECT t FROM Token t WHERE t.users.id = :userId  AND t.tokenType = :tokenType AND t.basicTokenEnum = 'Access_Token'")
    List<Token> getAllValidTokens(@Param("userId") Long userId, @Param("tokenType") TokenTypeEnum tokenType);

    @Query("SELECT t FROM Token t WHERE t.users.id = :userId  AND t.tokenType = :tokenType AND t.basicTokenEnum = 'Access_Token'")
    List<Token> getAllInValidTokens(@Param("userId") Long userId, @Param("tokenType") TokenTypeEnum tokenType);


    @Query("SELECT t FROM Token t WHERE t.users.id = :userId  AND t.tokenType = :tokenType AND t.basicTokenEnum = 'Refresh_Token'")
    List<Token> getAllValidRefreshTokens(@Param("userId") Long userId, @Param("tokenType") TokenTypeEnum tokenType);

    @Query("SELECT t FROM Token t WHERE t.users.id = :userId  AND t.tokenType = :tokenType AND t.basicTokenEnum = 'Refresh_Token'")
    List<Token> getAllInValidRefreshTokens(@Param("userId") Long userId, @Param("tokenType") TokenTypeEnum tokenType);

    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t WHERE t.users.id = :id")
    Optional<Token> findTokenByUserId(@Param("id") Long id);

    @Query("SELECT t FROM Token t " +
            "WHERE t.users.email = ?1 ")
    List<Token> findAllByUserEmail(String email);


//    @Modifying
//    @Transactional
////    @Query("DELETE FROM Token t WHERE t.isExpire = true AND t.isRevoke = true")
//    void deleteAllRevokeTokens();

    @Query("SELECT t FROM Token t WHERE t.users.id = :id")
    List<Token> findAllByUsersId(@Param("id") Long id);





}
