package com.mnit.erp.state.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnit.erp.academic.student.incomeCategory.model.FamilyIncomeCategory;
import com.mnit.erp.country.model.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class State {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name="country_id_fk"))
    private Country country;

    String stateType; // STATE, UT

    @Transient
    Long countryId;

    public void setCountryId(Long countryId){
        this.country = Objects.nonNull(countryId) ? new Country(countryId) : null;
    }

    public Long getCountryId(){
        return Objects.nonNull(this.country) ? this.country.getId() : null;
    }

    public State(Long stateId) {
        this.id = stateId;
    }
}
