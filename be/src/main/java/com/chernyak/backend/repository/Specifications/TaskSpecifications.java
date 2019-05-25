package com.chernyak.backend.repository.Specifications;

import com.chernyak.backend.entity.Task;
import com.chernyak.backend.entity.User;
import com.chernyak.backend.entity.enums.TaskPriority;
import com.chernyak.backend.entity.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecifications {
    private TaskSpecifications() {

    }

    public static Specification<Task> filter(final String status, final String priority) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (StringUtils.hasText(status)) {
                predicates.add(cb.equal(root.get("taskStatus"), TaskStatus.valueOf(status)));
            }

            if (StringUtils.hasText(priority)) {
                predicates.add(cb.equal(root.get("priority"), TaskPriority.valueOf(priority)));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}
