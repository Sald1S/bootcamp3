package com.saldis.SpringCRM.services;

import com.saldis.SpringCRM.entity.Operator;
import com.saldis.SpringCRM.entity.Request;
import com.saldis.SpringCRM.repository.OperatorsRepo;
import com.saldis.SpringCRM.repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private OperatorsRepo operatorsRepo;

    public List<Request> getAllRequests(){
        return requestRepo.findAll();
    }
    public Request addRequest(Request request){
        requestRepo.save(request);
        return request;
    }

    public Request getRequest(Long id){
        return requestRepo.findById(id).orElse(null);
    }

    public void deleteRequest(Request request) {
        requestRepo.delete(request);
    }

    public Request assignOperator(Long reqId,ArrayList<Long> operId) {
        Request request = requestRepo.findById(reqId).orElse(null);
        request.setHandled(true);
        ArrayList<Operator> operat = new ArrayList<>();
        if (request != null) {
            for (Long l : operId) {
                operat.add(operatorsRepo.findById(l).orElse(null));
            }
            request.setOperators(operat);
            requestRepo.save(request);
        }
        return request;
    }
    public Request unAssignOperator(Long operid,Long reqId) {
        Request request = requestRepo.findById(reqId).orElse(null);
        if (request != null) {
            Operator operator = operatorsRepo.findById(operid).orElse(null);
            if (operator != null) {
                List<Operator> operators = request.getOperators();
                operators.remove(operator);
                request.setOperators(operators);
                requestRepo.save(request);
            }
        }
        return request;
    }

    public List<Request> searchRequest(String key,String order) {
        List<Request> requests = null;

        if (order!=null){
            if(order.equals("desc")) {
                requests = requestRepo.searchRequestDesc("%"+key.toLowerCase()+"%");
            }
        }
        if (requests==null) {
            requests = requestRepo.searchRequestAsc("%"+key.toLowerCase()+"%");
        }
        return requests;
    }
}
