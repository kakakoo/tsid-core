package com.tsid.domain.entity.group;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.tsid.domain.entity.group.QGroup.group;


@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Group findByGroupId(Long groupId) {
        return jpaQueryFactory.selectFrom(group)
                .where(group.id.eq(groupId))
                .fetchOne();
    }
}
