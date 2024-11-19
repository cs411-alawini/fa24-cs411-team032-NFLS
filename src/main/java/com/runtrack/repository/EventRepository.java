package com.runtrack.repository;

import com.runtrack.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findByDate(LocalDate date); // 根据日期查询事件

    List<Event> findByLocation(String location); // 根据地点查询事件
}
