package edu.touro.las.mcon364.taskmanager;


import static java.util.Objects.hash;

public record Task(String name, Priority priority){
    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Task(String name1, Priority priority1))) return false;

        return name.equals(name1) && priority == priority1;

    }




    @Override

    public int hashCode() {

        return hash(name, priority);

    }
}



