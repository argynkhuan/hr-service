package io.hrmodule.hrservice.repository;

import io.hrmodule.hrservice.document.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VacancyRepository extends MongoRepository<Vacancy, String> {

}
