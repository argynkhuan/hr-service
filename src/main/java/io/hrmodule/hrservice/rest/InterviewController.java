package io.hrmodule.hrservice.rest;

import io.hrmodule.hrservice.document.Interview;
import io.hrmodule.hrservice.exception.ResourceNotFoundException;
import io.hrmodule.hrservice.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class InterviewController {
    @Autowired
    private InterviewRepository interviewRepository;


    @GetMapping("/interviews")
    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    @GetMapping("/interviews/{status}")
    public List<Interview> getInterviewsByStatus(@PathVariable(value = "status") String interviewStatus) {
        return interviewRepository.findAllByStatus(interviewStatus);
    }

    @GetMapping("/interview/{id}")
    public ResponseEntity<Interview> getInterviewById(@PathVariable(value = "id") String interviewId)
            throws ResourceNotFoundException {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id :: " + interviewId));
        return ResponseEntity.ok().body(interview);
    }

    @PostMapping("/interview")
    public Interview createInterview(@Valid @RequestBody Interview interview) {
        return interviewRepository.save(interview);
    }

    @PutMapping("/interview/{id}")
    public ResponseEntity<Interview> updateInterview(@PathVariable(value = "id") String interviewId,
                                                     @Valid @RequestBody Interview interviewDetails) throws ResourceNotFoundException {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id :: " + interviewId));


        interview.setStatus(interviewDetails.getStatus());
        interview.setAddress(interviewDetails.getAddress());
        interview.setCandidate(interviewDetails.getCandidate());
        interview.setEmployees(interviewDetails.getEmployees());
        interview.setDate(interviewDetails.getDate());

        final Interview updatedInterview = interviewRepository.save(interview);
        return ResponseEntity.ok(updatedInterview);
    }

    @DeleteMapping("/interview/{id}")
    public Map<String, Boolean> deleteInterview(@PathVariable(value = "id") String interviewId)
            throws ResourceNotFoundException {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id :: " + interviewId));

        interviewRepository.delete(interview);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
