package com.centene.centene.controller;

import com.centene.centene.domain.Enrollee;
import com.centene.centene.service.DependentService;
import com.centene.centene.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController()
@RequestMapping("/enrollee")
public class EnrolleeController {

    @Autowired
    DependentService dependentService;

    @Autowired
    EnrolleeService enrolleeService;

    @GetMapping()
    public ResponseEntity<?> getAllEnrollee() {
        return ResponseEntity.of(enrolleeService.getAllEnrollee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrolleeById(@PathVariable Long id) {

        Optional<Enrollee> enrollee = enrolleeService.getEnrollee(id);

        if (enrollee.isPresent()) {
            return new ResponseEntity<>(enrollee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public HttpEntity<Enrollee> addEnrollee(@Valid @RequestBody Enrollee enrollee) {


        Enrollee newEnrollee = enrolleeService.addEnrollee(enrollee);

        if (newEnrollee != null) {
            newEnrollee = enrolleeService.addEnrollee(enrollee);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newEnrollee, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateEnrollee(@RequestBody Enrollee enrollee, @PathVariable Long id) {

        Enrollee updatedEnrollee = null;

        Optional<Enrollee> enrolleeExists = enrolleeService.getEnrollee(id);
        if (enrolleeExists.isPresent()) {
            updatedEnrollee = enrolleeService.updateEnrollee(enrollee);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedEnrollee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEnrollee(@PathVariable Long id) {

        Optional<Enrollee> enrolleeExists = enrolleeService.getEnrollee(id);
        if (enrolleeExists.isPresent()) {
            enrolleeService.deleteEnrollee(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
