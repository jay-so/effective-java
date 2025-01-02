package staticUtility;

import java.util.List;

public class SpellChecker {
    private static final Lexicon dictionary = ...;

    private SpellChecker(){}

    public static boolean isValid(String word){ ...}
    public List<String> suggestions(String type) {...}
}
