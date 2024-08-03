package com.swiss.sharing.client.service.repositories;

import com.swiss.sharing.client.service.entity.UserToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends MongoRepository<UserToken, String> {

    Optional<UserToken> findByEmailAndToken(String email, String token);
}
