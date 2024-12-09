package com.runtrack.repository;

import com.runtrack.entity.Event;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

    // 手动编写 SQL 查询来根据城市查找事件
    @Query("SELECT * FROM Event WHERE City = :city")
    List<Event> findByCity(String city);

    // 手动编写 SQL 查询来根据日期查找事件
    @Query("SELECT * FROM Event WHERE Date = :date")
    List<Event> findByDate(LocalDate date);
}
