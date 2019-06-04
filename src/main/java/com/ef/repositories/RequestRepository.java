package com.ef.repositories;

import com.ef.entities.RequestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends CrudRepository<RequestEntity, Long> {
    String getExceededQuery = "SELECT ip " +
            "FROM requests " +
            "WHERE date BETWEEN :startDate AND :startDate + INTERVAL :hours HOUR " +
            "GROUP BY ip " +
            "HAVING COUNT(1) > :threshold";

    String isIpExceededQuery = "SELECT EXISTS(" +
            "SELECT 1 " +
            "   FROM requests " +
            "   WHERE ip = :ip " +
            "   HAVING COUNT(1) > :threshold " +
            ") AS isExceeded " +
            "FROM DUAL";

    @Query(value = getExceededQuery, nativeQuery = true)
    List<String> getExceeded(@Param("startDate") String startDate,
                                 @Param("threshold") Integer threshold,
                                 @Param("hours") Integer hours);

    @Query(value = isIpExceededQuery, nativeQuery = true)
    Byte isIpExceeded(@Param("ip") String ip, @Param("threshold") Integer threshold);
}
