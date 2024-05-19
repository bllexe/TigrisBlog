package com.tigrisblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "authors")
@Entity
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;
  @Column(name = "bio")
  private String bio;
  @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Article> articles;
}
