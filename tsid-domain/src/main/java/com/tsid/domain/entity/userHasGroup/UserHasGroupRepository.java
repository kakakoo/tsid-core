package com.tsid.domain.entity.userHasGroup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasGroupRepository extends JpaRepository<UserHasGroup, Long>, UserHasGroupRepositoryCustom {
}
