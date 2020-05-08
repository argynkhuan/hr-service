package io.hrmodule.hrservice.document;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Document("candidate")
@TypeAlias("Candidate")
public class Candidate {

    @JsonProperty("id")
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private String phoneNumber;

    @NotNull
    private String email;

    private String file;
}
