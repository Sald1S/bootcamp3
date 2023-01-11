package com.saldis.SpringCRM.repository;

import com.saldis.SpringCRM.entity.Request;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RequestRepo extends JpaRepository<Request,Long> {

    List<Request> findAllByHandledTrue();
    List<Request> findAllByHandledIsTrue();

    @Query(value = "SELECT request FROM Request request WHERE request.userName LIKE :nameParam ORDER BY request.userName ASC")
    List<Request> searchRequestAsc(String nameParam);

    @Query(value = "SELECT request FROM Request request WHERE request.userName LIKE :nameParam ORDER BY request.userName DESC")
    List<Request> searchRequestDesc(String nameParam);

    List<Request> findAllByUserNameOrderByUserNameAsc(String key);
    List<Request> findAllByUserNameOrderByUserNameDesc(String key);
}
