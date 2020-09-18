package com.centene.centene.controller;

import com.centene.centene.domain.Dependent;
import com.centene.centene.domain.Enrollee;
import com.centene.centene.service.DependentService;
import com.centene.centene.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dependent")
public class DependentController {

    @Autowired
    DependentService dependentService;

    @Autowired
    EnrolleeService enrolleeService;

    @GetMapping()
    public ResponseEntity<?> getAllDependent() {
        return new ResponseEntity<>(dependentService.getAllDependents(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDependent(@PathVariable Long id) {
        Optional<Dependent> dependent = dependentService.getDependent(id);
        if (dependent.isPresent()) {
            return new ResponseEntity<>(dependent, OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @PostMapping(value = "/create/{enrolleeId}/enrollee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDependent(@RequestBody Dependent dependent, @PathVariable Long enrolleeId) {

        Optional<Enrollee> enrollee = enrolleeService.getEnrollee(enrolleeId);

        if (enrollee.isPresent()) {

            Enrollee oldEnrolle = enrollee.get();
            dependent.setEnrollee(oldEnrolle);

            dependentService.addDependent(dependent);

            return new ResponseEntity<>(dependent, OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateDependant(@RequestBody Dependent dependent, @PathVariable Long id) {
        Optional<Dependent> oldDependent = dependentService.getDependent(id);
        if (oldDependent.isPresent()) {
            Dependent prevDependent = oldDependent.get();
            prevDependent.setDob(dependent.getDob());
            prevDependent.setName(dependent.getName());
            try {
                Dependent updatedDependent = dependentService.updateDependent(prevDependent);
                return new ResponseEntity<>(updatedDependent, OK);
            } catch (Exception e) {
                return new ResponseEntity<>(NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteDependent(@PathVariable Long id) {
        Optional<Dependent> oldDependent = dependentService.getDependent(id);
        if (oldDependent.isPresent()) {

            try {
                dependentService.deleteDependent(id);
                return new ResponseEntity<>(OK);
            } catch (Exception e) {
                return new ResponseEntity<>(NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(NOT_ACCEPTABLE);
        }
    }

}
