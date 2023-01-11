package com.saldis.SpringCRM.controller;

import com.saldis.SpringCRM.entity.Operator;
import com.saldis.SpringCRM.entity.Request;
import com.saldis.SpringCRM.repository.CoursesRepo;
import com.saldis.SpringCRM.repository.OperatorsRepo;
import com.saldis.SpringCRM.repository.RequestRepo;
import com.saldis.SpringCRM.services.CourseService;
import com.saldis.SpringCRM.services.OperatorService;
import com.saldis.SpringCRM.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OperatorService operatorService;

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "home";
    }

    @GetMapping(value = "/addrequest")
    public String addRequest(Model model) {
        model.addAttribute("courses", courseService.getCourse());
        return "addRequest";
    }

    @PostMapping(value = "/addrequest")
    public String addRequest(Request request) {
        requestService.addRequest(request);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}.html")
    public String details(Model model,
                          Request request) {
        model.addAttribute("crs", courseService.getCourse());
        model.addAttribute("request", requestService.getRequest(request.getId()));
        model.addAttribute("operators",operatorService.getOperator());
        return "details";
    }

    @PostMapping(value = "/delete")
    public String delete(Request request) {
        requestService.deleteRequest(request);
        return "redirect:/";
    }

    @GetMapping(value = "/true")
    public String truee(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "true";
    }

    @GetMapping(value = "/false")
    public String falsee(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "false";
    }

    @PostMapping(value = "/assign")
    public String assign(@RequestParam(name = "reqId") Long reqId,
                         @RequestParam(name = "operId") ArrayList<Long> operId) {
        Request request = requestService.assignOperator(reqId,operId);
        return "redirect:/details/" + request.getId() + ".html";
    }

    @PostMapping(value = "/remove")
    public String remove(@RequestParam(name = "OperatorId") Long operid,
                         @RequestParam(name = "requestId") Long reqId) {
        Request request = requestService.unAssignOperator(operid,reqId);
        return "redirect:/details/" + request.getId() + ".html";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(name = "key", required = false, defaultValue = "") String key,
                         @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                         Model model) {
        model.addAttribute("requests",requestService.searchRequest(key,order));
        return "search";
    }
}
