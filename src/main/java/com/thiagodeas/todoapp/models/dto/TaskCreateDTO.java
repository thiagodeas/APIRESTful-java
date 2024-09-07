package com.thiagodeas.todoapp.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class TaskCreateDTO {

    @NotBlank
    @Size(min = 1, max = 255)
    private String description;
    private Long id;
}
