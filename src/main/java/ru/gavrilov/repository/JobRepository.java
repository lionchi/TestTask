package ru.gavrilov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gavrilov.model.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAllBy();
    @Override
    <S extends Job> List<S> saveAll(Iterable<S> iterable);
}
