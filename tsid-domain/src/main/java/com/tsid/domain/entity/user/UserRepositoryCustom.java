package com.tsid.domain.entity.user;

public interface UserRepositoryCustom {

    User getUserById(Long userId);

    User findActiveUserByTel(String tel);
    User findActiveUserById(Long userId);

    User getUserByUuid(String uuid);

    User getExistUserByTel(String tel);

    User findDeletedUserByWithdrawalId(Long withdrawalId);
}
