package com.kodebjorn.repositories;

import com.kodebjorn.models.QuizEntry;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface QuizEntryRepository extends JpaRepository<QuizEntry, Integer> {

    List<QuizEntry> findByQuizTitle(String title);

}
