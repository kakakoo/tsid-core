package com.tsid.domain.entity.userHasGroup;

import com.tsid.domain.enums.group.EGroupStatusFlag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserHasGroupRepositoryCustom {

    UserHasGroup getUserHasGroupByCertIdAndUserId(Long certId, Long userId);

    UserHasGroup getUserHasGroupByUserIdAndGroupIdAndIsMaker(Long userId, Long groupId, Boolean isMaker);

    List<UserHasGroup> findUserHasGroupByGroupId(Long groupId);

    Page<UserHasGroup> findUserHasGroupsByUserId(Long userId, Pageable pageable);

    Long findUserHasGroupCountByUserId(Long userId);

    UserHasGroup findUserHasGroupByUserIdAndGroupIdAndGroupStatus(Long userId, Long groupId, EGroupStatusFlag status);

    UserHasGroup findMakerByGroupId(Long groupId);

    UserHasGroup findUserByUserIdAndGroupId(Long userId, Long groupId);

}
