package com.zuka.company.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.exception.CompanyException;
import com.zuka.company.exception.Problem;
import com.zuka.company.service.CompanyService;

@RestController()
@RequestMapping(value = "/api/company")
public class CompanyResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private CompanyService companyService;
    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    @Autowired
    private CompanyResource (CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable, CompanyDTO filter) {
        log.debug("REST request to get all Company");
        
        Page<CompanyDTO> listAllCompanyDTO = companyService.listAll(pageable, filter);
        return ResponseEntity.ok().body(listAllCompanyDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CompanyDTO CompanyDTO) {
    	log.debug("REST request to save Company : {}", CompanyDTO);
    	
        CompanyDTO CompanyReturnDTO = companyService.save(CompanyDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(CompanyReturnDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(CompanyReturnDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
    	log.debug("REST request to get Company : {}", id);
    	
        CompanyDTO CompanyDTO = companyService.findById(id);
        companyService.delete(CompanyDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteList(@PathVariable List<UUID> ids) {
    	log.debug("REST request to delete Company : {}", ids);
    	
        companyService.deleteList(ids);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CompanyDTO CompanyDTO) {
    	log.debug("REST request to update Company : {}", CompanyDTO);
    	
        CompanyDTO CompanySaveDTO = companyService.findById(CompanyDTO.getId());
        if(Objects.nonNull(CompanySaveDTO.getId())) {
            BeanUtils.copyProperties(CompanyDTO, CompanySaveDTO, "id");
            companyService.save(CompanySaveDTO);
        }
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ CompanyException.class })
    public ResponseEntity<Object> CompanyException(CompanyException ex) {
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
