package com.tsid.domain.entity.userHasGroup;

import com.tsid.domain.converter.group.GroupPositionConverter;
import com.tsid.domain.converter.group.GroupStatusConverter;
import com.tsid.domain.entity.BaseTimeEntity;
import com.tsid.domain.entity.group.Group;
import com.tsid.domain.entity.user.User;
import com.tsid.domain.enums.group.EGroupPositionFlag;
import com.tsid.domain.enums.group.EGroupStatusFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
@Table(	name = "user_has_group")
public class UserHasGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "invite_id")
    private User invite;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private User actionUser;

    @Convert(converter = GroupPositionConverter.class)
    @Column(name = "position_flag", nullable = false)
    private EGroupPositionFlag position;

    @Convert(converter = GroupStatusConverter.class)
    @Column(name = "status_flag", nullable = false, length = 20)
    private EGroupStatusFlag status;

    @Column
    private ZonedDateTime joinDate;

    public void updateGroupJoin() {
        this.joinDate = ZonedDateTime.now();
        this.status = EGroupStatusFlag.ACTIVE;
        this.actionUser = this.user;
    }

    public void updateStatus(EGroupStatusFlag status, User actionUser){
        this.status = status != null ? status : this.status;
        this.actionUser = actionUser != null ? actionUser : this.actionUser;
    }

    public void updatePosition(EGroupPositionFlag position) {
        /**
         * 그룹 생성자도 동의자이기 때문에 position은 동의자.
         * isMaker 는 true
         */
        this.position = position;
    }

    public void withdrawal() {
        this.status = EGroupStatusFlag.WITHDRAW;
    }

    public void updateUserStatus (User admin, EGroupStatusFlag status) {
        this.status = status;
        this.actionUser = admin;
    }

    public void updateMaker(EGroupPositionFlag position, User admin) {
        this.position = position;
        this.actionUser = admin;
    }


}
