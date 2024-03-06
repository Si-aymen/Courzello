package com.pidev.backend.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum Speciality {
    GENIECIVIL,MATH,IT,DS,ELECTROMECANIQUE,IOT,DESIGN,FR,ANG
}
