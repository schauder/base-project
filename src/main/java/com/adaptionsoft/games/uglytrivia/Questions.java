package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Questions {
    enum Categories {
        Pop,
        Science,
        Sports,
        Rock
    }

    private Map<Categories, LinkedList<String>> questions = new HashMap<>();

    {
        for (Categories category : Categories.values()) {
            questions.put(category, new LinkedList<String>());
            for (int i = 0; i < 50; i++) {
                questions.get(category).addLast(category + " Question " + i);
            }
        }
    }

    LinkedList<String> get(Categories category) {
        return questions.get(category);
    }

}
