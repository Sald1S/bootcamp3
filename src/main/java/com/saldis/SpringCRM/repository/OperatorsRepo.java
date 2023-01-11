package com.saldis.SpringCRM.repository;

import com.saldis.SpringCRM.entity.Operator;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OperatorsRepo extends JpaRepository<Operator, Long> {
}
