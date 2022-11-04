package com.tsid.domain.entity.groupCert;


import java.util.List;

public interface GroupCertRepositoryCustom {

    GroupCert getGroupCertByCertId(long certId);

    void updateGroupCertExpiredByGroupCertId(long certId);

    List<GroupCert> findGroupCertByGroupIds(List<Long> groupIds);

    GroupCert findGroupCertByGroupCertId(Long groupCertId);

}
