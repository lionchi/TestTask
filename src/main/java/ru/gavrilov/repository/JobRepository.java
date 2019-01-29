package ru.gavrilov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gavrilov.model.Job;
import ru.gavrilov.model.TypeTask;

import java.util.Date;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("select j from Job  j where (:user is null or j.user=:user) and (:typeTask is null or j.type=:typeTask) " +
            "and (:device is null or j.device=:device) and (:timeFrom is null or j.time>=:timeFrom) and (:timeTo is null or j.time<=:timeTo) order by j.time asc")
    List<Job> findAllBy(@Param("user") String user, @Param("typeTask") TypeTask typeTask, @Param("device") String device,
                        @Param("timeFrom") Date timeFrom, @Param("timeTo") Date timeTo);

    @Override
    <S extends Job> List<S> saveAll(Iterable<S> iterable);
}
