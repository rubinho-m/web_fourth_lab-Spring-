package com.rubinho.fourth_lab.repository;

import com.rubinho.fourth_lab.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("SELECT s FROM Point s WHERE s.username = :username")
    List<Point> findByUsername(@Param("username") String username);
}
