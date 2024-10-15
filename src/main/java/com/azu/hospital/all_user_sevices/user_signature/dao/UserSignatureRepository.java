package com.azu.hospital.all_user_sevices.user_signature.dao;

import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.all_user_sevices.user_signature.entity.UserSignature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserSignatureRepository extends JpaRepository<UserSignature, Long> {

//    Optional<UserSignature> findUserSignaturesByUserId(Long userId);


    Optional<UserSignature> findUserSignaturesByFingerprint(byte[] fingerprint);
}
