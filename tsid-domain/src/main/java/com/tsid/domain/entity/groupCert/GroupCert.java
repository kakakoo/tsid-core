package com.tsid.domain.entity.groupCert;

import com.tsid.domain.converter.cert.CertConverter;
import com.tsid.domain.entity.BaseTimeEntity;
import com.tsid.domain.entity.certRole.CertRole;
import com.tsid.domain.entity.company.Company;
import com.tsid.domain.entity.group.Group;
import com.tsid.domain.entity.groupCertHistory.GroupCertHistory;
import com.tsid.domain.enums.cert.ECertFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
@Table(	name = "group_cert")
public class GroupCert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(length = 64)
    private String stateCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cert_role_id")
    private CertRole certRole;

    @Column
    private Long callbackId;

    @Column
    private int certValue;

    @Column
    private int referrerValue;

    @Column
    private int certCount;

    @Column(length = 20)
    private String ipAddress;

    @Column(length = 20)
    private String certAddress;

    @Column(length = 100)
    private String reqLocation;

    @Column(length = 100)
    private String certLocation;

    @Column(length = 100)
    private String certDevice;

    @Convert(converter = CertConverter.class)
    @Column(name = "cert_status")
    private ECertFlag status;

    @OneToMany(mappedBy = "groupCert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupCertHistory> groupCertHistories;

    @Column
    private Boolean isCert;

    @Column
    private Boolean isExpired;

    @Column
    private Boolean isGroup;

    @Column
    private ZonedDateTime expireDate;

    public void updateCertAuth(ECertFlag status) {
        if (!this.status.equals(ECertFlag.PROGRESS)) {
            return;
        }
        this.status = status;
        if (status.equals(ECertFlag.SUCCESS)){
            this.isCert = true;
        }
    }

    public void updateCallback(){
        this.stateCode = "";
    }

    public void updateExpired() {
        this.status = ECertFlag.EXPIRED;
        this.isExpired = true;
        this.stateCode = "";
    }

    public void updateCertLocation(String certIpAddr, String certLocation, String device) {
        this.certAddress = certIpAddr;
        this.certLocation = certLocation;
        this.certDevice = device;
    }
}
