package com.tigrisblog.repos;

import com.tigrisblog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {


  Optional<Image> findByArticleId(Long articleId);

}
