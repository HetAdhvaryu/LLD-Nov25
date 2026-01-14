package advancedConcepts.designPatterns.prototypeAndRegistry;

import java.util.HashMap;

public class StudentRegistry { //TODO: make it singleton for maintaining a single place for all prototype objects
    private HashMap<String, Student> studentRegistry;

    public StudentRegistry() {
        this.studentRegistry = new HashMap<>();
    }

    public void addStudentPrototype(String batchName, Student batchStudentPrototype) {
        studentRegistry.put(batchName, batchStudentPrototype);
    }

    public Student getStudentPrototype(String batchName) {
        return studentRegistry.get(batchName);
    }
}
