package com.projeto.adamod4.repository;


import com.projeto.adamod4.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}

