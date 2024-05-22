package com.tigrisblog.repos;

import com.tigrisblog.entity.AboutMe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutmeRepository extends JpaRepository<AboutMe, Long> {
}
