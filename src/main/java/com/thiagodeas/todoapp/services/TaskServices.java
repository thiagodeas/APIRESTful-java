package com.thiagodeas.todoapp.services;

import com.thiagodeas.todoapp.models.Task;
import com.thiagodeas.todoapp.models.User;
import com.thiagodeas.todoapp.repositories.TaskRepository;
import com.thiagodeas.todoapp.services.exceptions.DataBindingViolation;
import com.thiagodeas.todoapp.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserServices userServices;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFound(
                "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long id){
        return this.taskRepository.findByUser_Id(id);
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userServices.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolation(
                    "Não é possível excluir pois há entidades relacionadas!"
            );
        }

    }
}
