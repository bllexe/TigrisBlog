package com.tigrisblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
@Data
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "title")
  private  String title;
  @Column(columnDefinition = "TEXT")
  @Lob
  private String content;
  @Column(name = "created_at")
  @CreatedDate
  private String created_at;
  @LastModifiedDate
  @Column(name = "updated_at")
  private String updated_at;
  @Column(name = "image_url")
  private String imageUrl;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;
  @ManyToMany
  @JoinTable(
      name = "article_category",
      joinColumns = @JoinColumn(name = "article_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;

}
