package com.mnit.erp.category.service;

import com.mnit.erp.category.model.Category;
import com.mnit.erp.category.repository.CategoryRepository;
import com.mnit.erp.exceptions.ServiceException;
import com.mnit.erp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category add(Category category) {
        Category category1 = this.categoryRepository.findByAbbreviationOrName(category.getAbbreviation(), category.getName());
        if(Objects.nonNull(category1)){
            throw new ServiceException("Category : " + category.getAbbreviation() + " already exists. Can't add new!");
        }
        if(this.validate(category)){
            return this.categoryRepository.save(category);
        }
        return null;
    }

    private boolean validate(Category category){
        return true;
    }

    public Category update(Category category) {
        Category category1 = this.categoryRepository.findById(category.getId()).orElse(null);
        if(Objects.isNull(category1)){
            throw new ServiceException("Category not found. Can't update!");
        }
        if(this.validate(category)){
            CommonUtils.copyNonNullProperties(category, category1);
            return this.categoryRepository.save(category);
        }
        return null;
    }

    public Category find(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }
}
