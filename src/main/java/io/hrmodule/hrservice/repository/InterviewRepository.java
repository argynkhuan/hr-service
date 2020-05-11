package io.hrmodule.hrservice.repository;

import io.hrmodule.hrservice.document.Candidate;
import io.hrmodule.hrservice.document.Interview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InterviewRepository extends MongoRepository<Interview, String> {
    public List<Interview> findAllByStatus (String status);
}
