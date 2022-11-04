package com.tsid.domain.entity.group;

import com.tsid.domain.entity.BaseTimeEntity;
import com.tsid.domain.entity.groupCert.GroupCert;
import com.tsid.domain.entity.groupHasPermission.GroupHasPermission;
import com.tsid.domain.entity.userHasGroup.UserHasGroup;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
@Table(	name = "groups")
public class Group extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column
    private Boolean isAuto;

    @Column
    private Boolean isActive;

    @OneToMany(mappedBy = "group")
    private List<UserHasGroup> users;

    @OneToMany(mappedBy = "permission")
    private List<GroupHasPermission> permissions;

    @OneToMany(mappedBy = "group")
    private List<GroupCert> groupCerts;

    public void update(String name) {
        this.name = name != null ? name : this.name;
    }

    public void delete(){
        this.isActive = false;
    }
}