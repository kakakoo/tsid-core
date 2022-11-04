package com.tsid.domain.entity.groupCert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCertRepository extends JpaRepository<GroupCert, Long>, GroupCertRepositoryCustom {
}
