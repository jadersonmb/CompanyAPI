package com.zuka.company.repository;

import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.model.Company;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySpec {
    public static Specification<Company> searchDesc(CompanyDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(filter.getName()) && !filter.getName().isEmpty()) {
                predicates.add(builder.like(builder.lower(root.<String>get("name")),
                        "%".concat(filter.getName().toLowerCase()).concat("%")));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
