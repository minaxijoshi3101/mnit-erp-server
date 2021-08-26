package com.mnit.erp.category.repository;

import com.mnit.erp.category.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStatus(boolean status);
    List<Category> findByNameContaining(String searchString);
    Category findByAbbreviationOrName(String abbreviation, String name);
    Category findByAbbreviation(String abbreviation);
}
