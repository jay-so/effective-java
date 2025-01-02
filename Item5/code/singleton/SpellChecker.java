package singleton;

import java.util.List;

public class SpellChecker {
    private final Lexion dictionary = ...;

    private SpellChecker(){}
    public static SpellChecker INSTNACE = new SpellChecker();

    public static boolean isValid(String word){ ...}
    public List<String> suggestions(String type) {...}
}
