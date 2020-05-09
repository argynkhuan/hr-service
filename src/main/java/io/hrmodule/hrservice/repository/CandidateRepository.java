package io.hrmodule.hrservice.repository;

import io.hrmodule.hrservice.document.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
    public List<Candidate> findAllByStatus (String status);
}
