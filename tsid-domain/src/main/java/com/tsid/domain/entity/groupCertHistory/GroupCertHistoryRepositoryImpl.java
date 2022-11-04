package com.tsid.domain.entity.groupCertHistory;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tsid.domain.enums.cert.ECertFlag;
import com.tsid.domain.enums.cert.ECertHistoryFlag;
import com.tsid.domain.enums.group.EGroupPositionFlag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.tsid.domain.entity.groupCertHistory.QGroupCertHistory.groupCertHistory;
import static com.tsid.domain.entity.userHasGroup.QUserHasGroup.userHasGroup;

@RequiredArgsConstructor
public class GroupCertHistoryRepositoryImpl implements GroupCertHistoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public GroupCertHistory getGroupCertHistoryByUserIdAndCertId(long userId, long certId) {
        return jpaQueryFactory
                .selectFrom(groupCertHistory)
                .where(groupCertHistory.groupCert.id.eq(certId),
                        groupCertHistory.user.id.eq(userId))
                .fetchOne();
    }

    @Override
    public List<GroupCertHistory> getCertGroupCertHitoryByCertId(long certId) {
        return jpaQueryFactory
                .select(groupCertHistory)
                .from(groupCertHistory)
                .where(groupCertHistory.groupCert.id.eq(certId),
                        groupCertHistory.position.in(EGroupPositionFlag.MAKER, EGroupPositionFlag.CONSENTER),
                        groupCertHistory.status.in(ECertHistoryFlag.CANCEL, ECertHistoryFlag.REJECT, ECertHistoryFlag.SUCCESS))
                .fetch();
    }

    @Override
    public Page<GroupCertHistory> findGroupHistoryByNameAndAuthPurpose(String name, List<ECertFlag> certFlags, ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable) {
        List<GroupCertHistory> content = jpaQueryFactory
                .selectFrom(groupCertHistory)
                .where(userNameEqual(name),
                        statusEqual(certFlags),
                        groupCertHistory.groupCert.createDate.between(startDate, endDate))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(groupCertHistory.groupCert.createDate.desc())
                .groupBy(groupCertHistory.groupCert.id)
                .fetch();
        return new PageImpl<>(content, pageable, content.size());
    }

    @Override
    public Long getTotalGroupHistoryByNameAndAuth(String name, List<ECertFlag> certFlags, ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable) {
        return jpaQueryFactory
                .select(count(groupCertHistory))
                .from(groupCertHistory)
                .where(userNameEqual(name),
                        statusEqual(certFlags),
                        groupCertHistory.groupCert.createDate.between(startDate, endDate))
                .fetchOne();
    }
    @Override
    public GroupCertHistory findMakerGroupCertHistoryByCertId(Long groupId, Long makerId) {
        return jpaQueryFactory.selectFrom(groupCertHistory)
                .where(groupCertHistory.groupCert.group.id.eq(groupId))
                .innerJoin(userHasGroup)
                .on(userHasGroup.user.id.eq(makerId),
                        userHasGroup.group.id.eq(groupId),
                        userHasGroup.position.eq(EGroupPositionFlag.MAKER))
                .groupBy(userHasGroup.user.id)
                .fetchOne();
    }

    @Override
    public List<GroupCertHistory> findGroupHistoryByGroupId(Long groupId) {
        return jpaQueryFactory.selectFrom(groupCertHistory)
                .where(groupCertHistory.groupCert.group.id.eq(groupId))
                .innerJoin(userHasGroup)
                .on(userHasGroup.group.id.eq(groupId))
                .fetch();
    }

    private BooleanExpression userNameEqual(String name) {
        if (name == null) {
            return null;
        }
        return groupCertHistory.user.name.eq(name);
    }

    private BooleanExpression statusEqual(List<ECertFlag> certFlags) {
        if (certFlags.isEmpty()) {
            return null;
        }
        return groupCertHistory.groupCert.status.in(certFlags);
    }

}
