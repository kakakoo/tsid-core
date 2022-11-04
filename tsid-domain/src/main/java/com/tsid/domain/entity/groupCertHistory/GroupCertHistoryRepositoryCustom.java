package com.tsid.domain.entity.groupCertHistory;

import com.tsid.domain.enums.cert.ECertFlag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.List;

public interface GroupCertHistoryRepositoryCustom {

    GroupCertHistory getGroupCertHistoryByUserIdAndCertId(long userId, long certId);

    List<GroupCertHistory> getCertGroupCertHitoryByCertId(long certId);

    Page<GroupCertHistory> findGroupHistoryByNameAndAuthPurpose(String name, List<ECertFlag> certFlags, ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable);

    Long getTotalGroupHistoryByNameAndAuth(String name, List<ECertFlag> certFlags, ZonedDateTime startDate, ZonedDateTime endDate, Pageable pageable);

    GroupCertHistory findMakerGroupCertHistoryByCertId(Long groupId, Long makerId);

    List<GroupCertHistory> findGroupHistoryByGroupId(Long groupId);

}
