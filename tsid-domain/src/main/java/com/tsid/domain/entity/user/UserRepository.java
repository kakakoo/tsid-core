package com.tsid.domain.entity.user;

import com.tsid.domain.enums.EStatusFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByIdAndStatus(Long id, EStatusFlag status);

}
