package com.tsid.domain.entity.user;

import com.tsid.domain.converter.GenderConverter;
import com.tsid.domain.converter.StatusConverter;
import com.tsid.domain.converter.StringCryptoConverter;
import com.tsid.domain.entity.BaseAllTimeEntity;
import com.tsid.domain.entity.userHasCompany.UserHasCompany;
import com.tsid.domain.entity.userHasGroup.UserHasGroup;
import com.tsid.domain.entity.userPrivacy.UserPrivacy;
import com.tsid.domain.enums.EGenderFlag;
import com.tsid.domain.enums.EStatusFlag;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
@Table(	name = "user")
public class User extends BaseAllTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String uuid;

    @Convert(converter = StringCryptoConverter.class)
    @Column
    private String tel;

    @Convert(converter = StringCryptoConverter.class)
    @Column
    private String name;

    @Column(length = 20)
    private String carrier;

    @Convert(converter = GenderConverter.class)
    @Column
    private EGenderFlag gender;

    @Column(length = 6)
    private String birth;

    @Column
    private Boolean foreigner;

    @Column(length = 128, name = "image_path")
    private String image;

    @Column(length = 128)
    private String email;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status_flag", nullable = false, length = 20)
    private EStatusFlag status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "privacy_id")
    private UserPrivacy userPrivacy;

    @OneToMany(mappedBy = "user")
    private List<UserHasGroup> groups;

    @OneToMany(mappedBy = "user")
    private List<UserHasCompany> hasCompanies;

    @Column
    private String fidoRegisterKey;

    @Column
    private Boolean isAlarm;

    @Column
    private Boolean isFidoRegister;

    @Column
    private Long updateId;

    @Column
    private Long deleteId;

    public void updateAlert(Boolean isAlarm){
        this.isAlarm = isAlarm != null ? isAlarm : this.isAlarm;
    }

    public void update(String tel, String name){
        this.tel = tel != null ? tel : this.tel;
        this.name = name != null ? name : this.name;
    }

    public void updateUserPrivacy(String disabledNo){
        this.userPrivacy.update(disabledNo);
    }

    public void delete(Long adminId) {
        this.tel = "탈퇴한 회원";
        this.carrier = null;
        this.gender = EGenderFlag.NONE;
        this.birth = null;
        this.foreigner = null;
        this.status = EStatusFlag.DELETE;
        this.deleteId = adminId;
        this.setDeleteDate(ZonedDateTime.now());
    }

    public void updateFidoRegisterKey(String tel, String name, String carrier, boolean foreigner, String birth, EGenderFlag genderFlag, String fidoRegisterKey) {
        this.tel = tel != null ? tel : this.tel;
        this.name = name != null ? name : this.name;
        this.carrier = carrier != null ? carrier : this.carrier;
        this.foreigner = foreigner;
        this.fidoRegisterKey = fidoRegisterKey;
        this.isFidoRegister = false;
        this.birth = birth != null ? birth : this.birth;
        this.gender = genderFlag;
    }

    public void updateRegisterInfo(){
        this.fidoRegisterKey = "";
        this.isFidoRegister = true;
    }

}
