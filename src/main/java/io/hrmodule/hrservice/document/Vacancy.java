package io.hrmodule.hrservice.document;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Document("vacancy")
@TypeAlias("Vacancy")
public class Vacancy {

    @JsonProperty("id")
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private List<Employee> employees;

    private List<Candidate> candidates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
