package com.saldis.SpringCRM.services;

import com.saldis.SpringCRM.entity.Operator;
import com.saldis.SpringCRM.repository.OperatorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService {

    @Autowired
    private OperatorsRepo operatorsRepo;

    public List<Operator> getOperator(){
        return operatorsRepo.findAll();
    }
}
