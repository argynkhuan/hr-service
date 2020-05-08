package io.hrmodule.hrservice.repository;

import io.hrmodule.hrservice.document.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, String> {

}
