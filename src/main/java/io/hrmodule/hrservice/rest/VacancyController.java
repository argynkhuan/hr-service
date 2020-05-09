package io.hrmodule.hrservice.rest;

import io.hrmodule.hrservice.document.Vacancy;
import io.hrmodule.hrservice.exception.ResourceNotFoundException;
import io.hrmodule.hrservice.repository.VacancyRepository;
import io.hrmodule.hrservice.service.NotificationService;
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
public class VacancyController {
    @Autowired
    private VacancyRepository vacancyRepository;

    private NotificationService notificationService;

    @GetMapping("/vacancies")
    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    @GetMapping("/vacancy/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable(value = "id") String vacancyId)
            throws ResourceNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found for this id :: " + vacancyId));
        return ResponseEntity.ok().body(vacancy);
    }

    @PostMapping("/vacancy")
    public Vacancy createVacancy(@Valid @RequestBody Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @PutMapping("/vacancy/{id}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable(value = "id") String vacancyId,
                                                 @Valid @RequestBody Vacancy vacancyDetails) throws ResourceNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found for this id :: " + vacancyId));

        vacancy.setName(vacancyDetails.getName());
        vacancy.setEmployees(vacancyDetails.getEmployees());
        vacancy.setCandidates(vacancyDetails.getCandidates());
        final Vacancy updatedVacancy = vacancyRepository.save(vacancy);
        return ResponseEntity.ok(updatedVacancy);
    }

    @DeleteMapping("/vacancy/{id}")
    public Map<String, Boolean> deleteVacancy(@PathVariable(value = "id") String vacancyId)
            throws ResourceNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found for this id :: " + vacancyId));

        vacancyRepository.delete(vacancy);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
