package com.tsid.domain.entity.company;


import com.tsid.domain.converter.StatusConverter;
import com.tsid.domain.entity.BaseAllTimeEntity;
import com.tsid.domain.entity.companyCallback.CompanyCallback;
import com.tsid.domain.entity.companyHasCertRole.CompanyHasCertRole;
import com.tsid.domain.entity.userHasCompany.UserHasCompany;
import com.tsid.domain.enums.EStatusFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
@Table(	name = "company")
public class Company extends BaseAllTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 128, name = "image_path")
    private String image;

    @Column
    private String lang;

    @Column
    private String introduce;

    @Column(length = 128)
    private String companyUrl;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status_flag", nullable = false, length = 20)
    private EStatusFlag status;

    @OneToMany(mappedBy = "company")
    private List<CompanyHasCertRole> companyHasCertRoles;

    @OneToMany(mappedBy = "company")
    private List<UserHasCompany> userHasCompanies;

    @OneToMany(mappedBy = "company")
    private List<CompanyCallback> companyCallbacks;

    @Column
    private Short sort;

    @Column
    private Long createId;

    @Column
    private Long updateId;

    @Column
    private Long deleteId;

    public void updateImage(long userId, String imagePath) {
        this.updateId = userId;
        this.image = imagePath;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public void update(String name, String introduce, String companyUrl, EStatusFlag status, Long updateId){
        this.name = name != null ? name : this.name;
        this.introduce = introduce != null ? introduce : this.introduce;
        this.companyUrl = companyUrl != null ? companyUrl : this.companyUrl;
        this.status = status != null ? status : this.status;
        this.updateId = updateId != null ? updateId : this.updateId;
    }

    public void delete(Long adminId){
        this.status = EStatusFlag.DELETE;
        this.deleteId = adminId != null ? adminId : this.deleteId;
    }

}
