package com.tsid.domain.entity.groupCertHistory;

import com.tsid.domain.converter.cert.CertHistoryConverter;
import com.tsid.domain.converter.group.GroupPositionConverter;
import com.tsid.domain.entity.groupCert.GroupCert;
import com.tsid.domain.entity.user.User;
import com.tsid.domain.enums.cert.ECertHistoryFlag;
import com.tsid.domain.enums.group.EGroupPositionFlag;
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
@Table(	name = "group_cert_history")
public class GroupCertHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_cert_id", nullable = false)
    private GroupCert groupCert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Convert(converter = CertHistoryConverter.class)
    @Column(name = "cert_status", nullable = false)
    private ECertHistoryFlag status;

    @Convert(converter = GroupPositionConverter.class)
    @Column(name = "position_flag", nullable = false)
    private EGroupPositionFlag position;

    @Column
    private String certDevice;

    @Column
    private String certIp;

    @Column
    private String certLocation;

    @Column
    private ZonedDateTime certDate;

    @Column
    private ZonedDateTime createDate;


    public void updateCert(Boolean isCert, String certDevice, String ip, String location) {
        this.certDevice = certDevice;
        this.certIp = ip;
        this.certLocation = location;
        if (isCert) {
            this.status = ECertHistoryFlag.SUCCESS;
        } else {
            this.status = ECertHistoryFlag.REJECT;
        }
    }

    public void updateCancel(){
        this.status = ECertHistoryFlag.CANCEL;
    }
}
