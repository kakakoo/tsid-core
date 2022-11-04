package com.tsid.domain.entity.groupCert;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tsid.domain.enums.cert.ECertFlag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.tsid.domain.entity.groupCert.QGroupCert.groupCert;

@RequiredArgsConstructor
public class GroupCertRepositoryImpl implements GroupCertRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public GroupCert getGroupCertByCertId(long certId) {
        return jpaQueryFactory
                .selectFrom(groupCert)
                .where(groupCert.id.eq(certId))
                .fetchOne();
    }

    @Override
    public void updateGroupCertExpiredByGroupCertId(long groupcertId) {
        jpaQueryFactory
                .update(groupCert)
                .set(groupCert.status, ECertFlag.EXPIRED)
                .where(groupCert.id.eq(groupcertId))
                .execute();
    }

    @Override
    public List<GroupCert> findGroupCertByGroupIds(List<Long> groupIds) {
        return jpaQueryFactory.selectFrom(groupCert)
                .where(groupCert.group.id.in(groupIds))
                .orderBy(groupCert.createDate.desc())
                .fetch();
    }

    @Override
    public GroupCert findGroupCertByGroupCertId(Long groupCertId) {
        return jpaQueryFactory.selectFrom(groupCert)
                .where(groupCert.id.eq(groupCertId))
                .fetchOne();
    }

}
