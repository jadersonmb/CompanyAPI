package com.zuka.category.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zuka.category.dto.CategoryDTO;
import com.zuka.category.exception.CategoryException;
import com.zuka.category.exception.Problem;
import com.zuka.category.service.CategoryService;

@RestController()
@RequestMapping(value = "/api/category")
public class CategoryResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private CategoryService categoryService;

    @Autowired
    private CategoryResource (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable, CategoryDTO filter) {
        Page<CategoryDTO> listAllCategoryDTO = categoryService.listAll(pageable, filter);
        return ResponseEntity.ok().body(listAllCategoryDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CategoryDTO categoryDTO) {
        CategoryDTO categoryReturnDTO = categoryService.save(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryReturnDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryReturnDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        CategoryDTO categoryDTO = categoryService.findById(id);
        categoryService.delete(categoryDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteList(@PathVariable List<UUID> ids) {
        categoryService.deleteList(ids);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categorySaveDTO = categoryService.findById(categoryDTO.getId());
        if(Objects.nonNull(categorySaveDTO.getId())) {
            BeanUtils.copyProperties(categoryDTO, categorySaveDTO, "id");
            categoryService.save(categorySaveDTO);
        }
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ CategoryException.class })
    public ResponseEntity<Object> CategoryException(CategoryException ex) {
        Problem problem = createProblemBuild(ex.getStatus(), ex.getDetails(), ex.getType(), ex.getTitle())
                .build();
        return ResponseEntity.badRequest().body(problem);
    }

    private Problem.ProblemBuilder createProblemBuild(Integer status, String detail, String type, String title){
        return Problem.builder()
                .status(status)
                .details(detail)
                .type(type)
                .title(title);
    }
}
