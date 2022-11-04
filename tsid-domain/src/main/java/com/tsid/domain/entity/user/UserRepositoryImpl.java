package com.tsid.domain.entity.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tsid.domain.enums.EStatusFlag;
import lombok.RequiredArgsConstructor;

import static com.tsid.domain.entity.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public User getUserById(Long userId) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }

    @Override
    public User findActiveUserByTel(String tel) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.tel.eq(tel))
                .fetchOne();
    }

    @Override
    public User findActiveUserById(Long userId) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.id.eq(userId), user.status.eq(EStatusFlag.ACTIVE))
                .fetchOne();
    }

    @Override
    public User getUserByUuid(String uuid) {
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.uuid.eq(uuid))
                .fetchOne();
    }

    @Override
    public User getExistUserByTel(String tel) {
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.tel.eq(tel),
                        user.status.eq(EStatusFlag.ACTIVE))
                .fetchOne();
    }

    @Override
    public User findDeletedUserByWithdrawalId(Long withdrawalId) {
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.id.eq(withdrawalId), user.status.eq(EStatusFlag.DELETE))
                .fetchOne();
    }
}
