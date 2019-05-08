package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Comment;
import com.chernyak.backend.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByTaskId(Pageable pageable, Long id);
}
