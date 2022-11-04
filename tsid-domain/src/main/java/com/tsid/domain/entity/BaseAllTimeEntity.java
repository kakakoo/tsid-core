package com.tsid.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAllTimeEntity {

    private ZonedDateTime createDate;
    private ZonedDateTime updateDate;
    private ZonedDateTime deleteDate;
}
