package edu.touro.las.mcon364.taskmanager;

import java.util.*;

public class TaskRegistry {
    private final Map<String, Task> tasks = new HashMap<>();

    public void add(Task task) {
        tasks.put(task.name(), task);
    }

    public Optional<Task> get(String name) {
        return Optional.ofNullable(tasks.get(name));
    }

    public void remove(String name) {
        tasks.remove(name);
    }

    public Map<String, Task> getAll() {
        return tasks;
    }
    public Map<Priority,List<Task>> getTasksByPriority() {
        List<Task> low= new ArrayList<>();
        List<Task> med=new ArrayList<>();
        List<Task> high=new ArrayList<>();
        Map<Priority,List<Task>>priorityListMap =new HashMap<>();
        for(String key :tasks.keySet()){
          Task t=  tasks.get(key);
          Priority priority= t.priority();
          switch (priority){
              case LOW -> low.add(t);
              case MEDIUM -> med.add(t);
              case HIGH -> high.add(t);
              default -> throw new PriorityNotFoundException(priority);
          }
        }
        priorityListMap.put(Priority.LOW,low);
        priorityListMap.put(Priority.MEDIUM,med);
        priorityListMap.put(Priority.HIGH,high);
        return priorityListMap;
    }


}
