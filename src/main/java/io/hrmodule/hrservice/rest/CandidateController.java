package io.hrmodule.hrservice.rest;

import io.hrmodule.hrservice.document.Candidate;
import io.hrmodule.hrservice.exception.ResourceNotFoundException;
import io.hrmodule.hrservice.repository.CandidateRepository;
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
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;


    @GetMapping("/candidates")
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @GetMapping("/candidates/{status}")
    public List<Candidate> getCandidatesByStatus(@PathVariable(value = "status") String candidateStatus) {
        return candidateRepository.findAllByStatus(candidateStatus);
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable(value = "id") String candidateId)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + candidateId));
        return ResponseEntity.ok().body(candidate);
    }

    @PostMapping("/candidate")
    public Candidate createCandidate(@Valid @RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable(value = "id") String candidateId,
                                                     @Valid @RequestBody Candidate candidateDetails) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + candidateId));


        candidate.setFullName(candidateDetails.getFullName());
        candidate.setSurname(candidateDetails.getSurname());
        candidate.setEmail(candidateDetails.getEmail());
        candidate.setPhoneNumber(candidateDetails.getPhoneNumber());
        candidate.setAttachment(candidateDetails.getAttachment());

        final Candidate updatedCandidate = candidateRepository.save(candidate);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/candidate/{id}")
    public Map<String, Boolean> deleteCandidate(@PathVariable(value = "id") String candidateId)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + candidateId));

        candidateRepository.delete(candidate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
