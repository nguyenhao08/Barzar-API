package com.example.BarzarAPI.repositories;

import com.example.BarzarAPI.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  // @Query("SELECT c FROM Category c WHERE c.title = :title AND c.description =
  // :description AND c.id = :id")
  // Category findCategory(@Param("title") String title, @Param("description")
  // String description, @Param("id") Long id);
}