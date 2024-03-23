package com.kodebjorn.repositories;

import com.kodebjorn.models.UserCredential;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends GenericRepository<UserCredential, Integer> {

    @Query(
            value =
                    """
                                select count(*) = 0 from UserCredential u
                                    where u.username = :username or u.email = :email
                            """
    )
    boolean isUniqueIgnoreCase(String username, String email);

    UserCredential save(UserCredential userCredential);

    Optional<UserCredential> findOne(Integer id);

    Optional<UserCredential> findByUsernameIgnoreCase(String username);

    void deleteByUsernameIgnoreCase(String username);

    Optional<UserCredential> findByEmailIgnoreCase(String email);

    void deleteByEmailIgnoreCase(String email);
}
