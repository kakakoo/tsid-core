package com.tsid.domain.entity.company;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.tsid.domain.entity.company.QCompany.company;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Company findCompanyById(Long companyId) {
        return jpaQueryFactory.selectFrom(company)
                .where(company.id.eq(companyId))
                .fetchOne();
    }
}
