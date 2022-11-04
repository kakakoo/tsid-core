package com.tsid.domain.entity.groupCertHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCertHistoryRepository extends JpaRepository<GroupCertHistory, Long>, GroupCertHistoryRepositoryCustom {
}
