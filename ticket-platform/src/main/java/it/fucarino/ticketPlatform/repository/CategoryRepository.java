package it.fucarino.ticketPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fucarino.ticketPlatform.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
