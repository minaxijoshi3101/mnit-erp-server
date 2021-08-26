package com.mnit.erp.academic.selectionboard.type.repository;

import com.mnit.erp.academic.selectionboard.type.model.SelectionBoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectionBoardTypeRepository extends JpaRepository<SelectionBoardType, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM selectionBoardType " +
                    "WHERE id in (" +
                    "SELECT sbtp.selectionBoardTypeId " +
                    "FROM selectionBoardTypeMapping sbtp " +
                    "WHERE sbtp.selectionBoardId = :selectionBoardId)"
    )
    List<SelectionBoardType> findAllUnderSelectionBoardId(@Param("selectionBoardId") Long selectionBoardId);

    SelectionBoardType findByName(String name);

    List<SelectionBoardType> findByIdIn(List<Long> ids);
}
