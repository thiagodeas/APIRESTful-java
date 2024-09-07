package com.thiagodeas.todoapp.controllers;

import com.thiagodeas.todoapp.models.Task;
import com.thiagodeas.todoapp.models.projection.TaskProjection;
import com.thiagodeas.todoapp.services.TaskServices;

import com.thiagodeas.todoapp.models.dto.TaskCreateDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskServices taskServices;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task obj = this.taskServices.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/user")
    public ResponseEntity<List<TaskProjection>> findAllByUser(){
        List<TaskProjection> objs = this.taskServices.findAllByUser();
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<TaskCreateDTO> create(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Task task = new Task();
        task.setDescription(taskCreateDTO.getDescription());
        Task createdTask = this.taskServices.create(task);

        TaskCreateDTO responseDTO = new TaskCreateDTO();
        responseDTO.setDescription(createdTask.getDescription());
        responseDTO.setId(createdTask.getId());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTask.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Task obj, @PathVariable Long id) {
        obj.setId(id);
        this.taskServices.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
