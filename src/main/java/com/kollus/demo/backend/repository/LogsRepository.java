package com.kollus.demo.eduwillvod.repository;

import com.kollus.demo.eduwillvod.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {
    @Query(value = "SELECT l FROM Logs l ORDER BY id DESC")
    List<Logs> findAllOrderByIdDesc();
}
