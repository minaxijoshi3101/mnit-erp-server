package com.mnit.erp.district.model;

import com.mnit.erp.state.model.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class District {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToOne
    State state;

    @Transient
    Long stateId;

    public District(Long districtId) {
        this.id = districtId;
    }

    public void setStateId(Long stateId){
        this.state = Objects.nonNull(state) ? new State(stateId) : null;
    }

    public Long getStateId() {
        return Objects.nonNull(this.state) ? this.state.getId() : null;
    }

}
