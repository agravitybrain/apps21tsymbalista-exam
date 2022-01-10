package domain;

import json.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private List<Json> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList<>();
        for (Tuple<String, Integer> exam: exams) {
            String subject = exam.key;
            Integer mark = exam.value;
            this.exams.add(new JsonObject(
                    new JsonPair("course", new JsonString(subject)),
                    new JsonPair("mark", new JsonNumber(mark)),
                    new JsonPair("passed", new JsonBoolean(mark >= 3))
            ));
        }
    }

    @Override
    public JsonObject toJsonObject() {
        return new JsonObject(
                new JsonPair("name", new JsonString(name)),
                new JsonPair("surname", new JsonString(surname)),
                new JsonPair("year", new JsonNumber(year)),
                new JsonPair("exams", new JsonArray(exams.toArray(new Json[0])))
        );
    }
}