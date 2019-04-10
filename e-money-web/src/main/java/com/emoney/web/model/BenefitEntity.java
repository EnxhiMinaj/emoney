package com.emoney.web.model;

import com.emoney.core.model.EntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "benefit")
public class BenefitEntity extends EntityBase {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "longitude")
    private float longitude;
    @Column(name = "start_date_time")
    private Date startDateTime;
    @Column(name = "end_date_time")
    private Date endDateTime;
    @Column(name = "credits")
    private int credits;
    @Column(name = "qr_code")
    private String qrCode;


}