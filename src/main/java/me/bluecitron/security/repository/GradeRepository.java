package me.bluecitron.security.repository;

import me.bluecitron.security.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByName(String name);
}
