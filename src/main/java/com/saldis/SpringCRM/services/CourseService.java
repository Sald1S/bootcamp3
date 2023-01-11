package com.saldis.SpringCRM.services;

import com.saldis.SpringCRM.entity.Courses;
import com.saldis.SpringCRM.repository.CoursesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CoursesRepo coursesRepo;

    public List<Courses> getCourse(){
        return coursesRepo.findAll();
    }
}
