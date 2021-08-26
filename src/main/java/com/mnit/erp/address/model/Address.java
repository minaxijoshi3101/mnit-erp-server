package com.mnit.erp.address.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnit.erp.academic.student.st.model.Student;
import com.mnit.erp.country.model.Country;
import com.mnit.erp.district.model.District;
import com.mnit.erp.state.model.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    AddressType addressType;
    String addressLine1;
    String addressLine2;
    String landMark;

    String city;

    @ManyToOne District district;
    @ManyToOne State state;
    @ManyToOne Country country;
    String pin;

    Boolean isValid;
    Date addedOn;
    Date updatedOn;

    @Transient Long districtId;
    @Transient Long stateId;
    @Transient Long countryId;

    public Long getDistrictId() {
        return Objects.nonNull(this.district) ? this.district.getId() : null;
    }

    public void setDistrictId(Long districtId) {
        if(Objects.isNull(this.district)){
            this.districtId = districtId;
            this.district = new District(districtId);
        }
    }

    public Long getStateId() {
        return Objects.nonNull(this.state) ? this.state.getId() : null;
    }

    public void setStateId(Long stateId) {
        if(Objects.isNull(this.state)){
            this.stateId = stateId;
            this.state = new State(stateId);
        }
    }

    public Long getCountryId() {
        return Objects.nonNull(this.country) ? this.country.getId() : null;
    }

    public void setCountryId(Long countryId) {
        if(Objects.isNull(this.country)){
            this.countryId = countryId;
            this.country = new Country(countryId);
        }
    }

}
