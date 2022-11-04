package com.tsid.domain.entity.userHasGroup;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tsid.domain.enums.group.EGroupPositionFlag;
import com.tsid.domain.enums.group.EGroupStatusFlag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.tsid.domain.entity.group.QGroup.group;
import static com.tsid.domain.entity.groupCert.QGroupCert.groupCert;
import static com.tsid.domain.entity.userHasGroup.QUserHasGroup.userHasGroup;

@RequiredArgsConstructor
public class UserHasGroupRepositoryImpl implements UserHasGroupRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UserHasGroup getUserHasGroupByCertIdAndUserId(Long certId, Long userId) {
        return jpaQueryFactory
                .select(userHasGroup)
                .from(groupCert)
                .join(group).on(groupCert.group.id.eq(group.id),
                        group.isActive.isTrue())
                .join(userHasGroup).on(group.id.eq(userHasGroup.group.id),
                        userHasGroup.user.id.eq(userId),
                        userHasGroup.status.in(EGroupStatusFlag.RELEASE, EGroupStatusFlag.ACTIVE))
                .where(groupCert.id.eq(certId))
                .fetchFirst();
    }

    @Override
    public UserHasGroup getUserHasGroupByUserIdAndGroupIdAndIsMaker(Long userId, Long groupId, Boolean isMaker) {
        return jpaQueryFactory
                .selectFrom(userHasGroup)
                .where(userHasGroup.user.id.eq(userId),
                        userHasGroup.group.id.eq(groupId),
                        userHasGroup.status.in(EGroupStatusFlag.RELEASE, EGroupStatusFlag.ACTIVE),
                        positionIsMaker(isMaker))
                .fetchOne();
    }


    @Override
    public List<UserHasGroup> findUserHasGroupByGroupId(Long groupId) {
        return jpaQueryFactory.selectFrom(userHasGroup)
                .where(userHasGroup.group.id.eq(groupId))
                .fetch();
    }

    @Override
    public Page<UserHasGroup> findUserHasGroupsByUserId(Long userId, Pageable pageable) {
        List<UserHasGroup> userHasGroups = jpaQueryFactory.selectFrom(userHasGroup)
                .where(userHasGroup.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageNumber())
                .fetch();
        return new PageImpl<>(userHasGroups, pageable, pageable.getPageSize());

    }

    @Override
    public Long findUserHasGroupCountByUserId(Long userId) {
        return jpaQueryFactory.select(count(userHasGroup))
                .from(userHasGroup)
                .where(userHasGroup.user.id.eq(userId))
                .fetchOne();
    }


    @Override
    public UserHasGroup findUserHasGroupByUserIdAndGroupIdAndGroupStatus(Long userId, Long groupId, EGroupStatusFlag status) {
        return jpaQueryFactory.selectFrom(userHasGroup)
                .where(userHasGroup.group.id.eq(groupId),
                        userHasGroup.user.id.eq(userId),
                        userHasGroup.status.eq(status))
                .fetchOne();
    }

    @Override
    public UserHasGroup findMakerByGroupId(Long groupId) {
        return jpaQueryFactory.selectFrom(userHasGroup)
                .where(userHasGroup.group.id.eq(groupId),
                        userHasGroup.position.eq(EGroupPositionFlag.MAKER))
                .fetchOne();
    }

    @Override
    public UserHasGroup findUserByUserIdAndGroupId(Long userId, Long groupId) {
        return jpaQueryFactory.selectFrom(userHasGroup)
                .where(userHasGroup.user.id.eq(userId),
                        userHasGroup.group.id.eq(groupId))
                .fetchOne();
    }


    private BooleanExpression positionIsMaker(Boolean isMaker) {
        if (isMaker) {
            return userHasGroup.position.eq(EGroupPositionFlag.MAKER);
        } else {
            return userHasGroup.position.ne(EGroupPositionFlag.MAKER);
        }
    }
}
