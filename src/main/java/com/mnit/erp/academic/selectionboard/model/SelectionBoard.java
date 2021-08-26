package com.mnit.erp.academic.selectionboard.model;

import com.mnit.erp.academic.selectionboard.type.model.SelectionBoardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SelectionBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String abbreviation;
    String name;
    Boolean status;

    @ManyToMany
    List<SelectionBoardType> selectionBoardTypes;

    @Transient
    List<Long> selectionBoardTypeIds;

    public SelectionBoard(Long selectionBoardId) {
        this.id = selectionBoardId;
        this.selectionBoardTypes = Arrays.asList();
        this.selectionBoardTypeIds = Arrays.asList();
    }

    public List<Long> getSelectionBoardTypeIds() {
        return this.selectionBoardTypes.stream().map(SelectionBoardType::getId).collect(Collectors.toList());
    }

    public void setSelectionBoardTypeIds(List<Long> selectionBoardTypeIds) {
        this.selectionBoardTypes = selectionBoardTypeIds.stream().map(SelectionBoardType::new).collect(Collectors.toList());;
    }
}
