package com.thiagodeas.todoapp.models.projection;

import com.thiagodeas.todoapp.models.enums.TaskPriorityEnum;

public interface TaskProjection {

    public Long getId();

    public String getDescription();

    public TaskPriorityEnum getPriority();

}
