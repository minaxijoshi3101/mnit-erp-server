package com.mnit.erp.category.controller;

import com.mnit.erp.AbstractController;
import com.mnit.erp.category.model.Category;
import com.mnit.erp.category.service.CategoryService;
import com.mnit.erp.response.CustomResponseMessage;
import com.mnit.erp.util.ResponseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CustomResponseMessage add(@RequestBody Category category){
        Category category1 = this.categoryService.add(category);
        ResponseMessageType responseMessageType = Objects.nonNull(category1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category1) ? "Category added successfully in masters!" : "Unable to add category in masters!")
                .messageType(responseMessageType)
                .response(category1).build();
    }

    @PutMapping
    public CustomResponseMessage update(@RequestBody Category category){
        Category category1 = this.categoryService.update(category);
        ResponseMessageType responseMessageType = Objects.nonNull(category1) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category1) ? "Category updated successfully in masters!" : "Unable to update category in masters!")
                .messageType(responseMessageType)
                .response(category1).build();
    }

    @GetMapping("/{id}")
    public CustomResponseMessage find(@PathVariable Long id){
        Category category = this.categoryService.find(id);
        ResponseMessageType responseMessageType = Objects.nonNull(category) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(category) ? "Category found in masters!" : "Unable to find category in masters!")
                .messageType(responseMessageType)
                .response(category).build();
    }

    @GetMapping
    public CustomResponseMessage findAll(){
        List<Category> categories = this.categoryService.findAll();
        ResponseMessageType responseMessageType = Objects.nonNull(categories) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage.builder().message(Objects.nonNull(categories) && categories.size() > 0 ? "Categories found in masters!" : "Unable to find categories in masters!")
                .messageType(responseMessageType)
                .response(categories).build();
    }

}
