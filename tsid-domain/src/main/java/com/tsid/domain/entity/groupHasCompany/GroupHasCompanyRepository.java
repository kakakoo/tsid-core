package com.tsid.domain.entity.groupHasCompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupHasCompanyRepository extends JpaRepository<GroupHasCompany, Long>, GroupHasCompanyRepositoryCustom {
}
