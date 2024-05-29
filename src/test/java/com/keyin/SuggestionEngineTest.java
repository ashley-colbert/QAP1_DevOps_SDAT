package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine = new SuggestionEngine();

    //Test to confirm that a common incorrectly spelled word will give a correct suggestion
    @Test
    public void testGenerateSuggestions() throws IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("abot").contains("about"));
    }

    //Test to confirm that if a word is spelled correctly no suggestion will be returned
    @Test
    public void testNoSuggestionForCorrectWords() throws IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("about").isEmpty());
    }

    //Tests that the results from the database are not null
    @Test
    public void testGenerateGetWordSuggestionsDB() {
        Map<String, Integer> actualWordMap = suggestionEngine.getWordSuggestionDB();
        Assertions.assertNotNull(actualWordMap, "Word map should not be null");
    }

    //A test to ensure that the suggestions produced by the generateSuggestions Stream will not exceed 10 lines.
    @Test
    public void testGenerateSuggestionLimitTen() throws IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        String suggestions = suggestionEngine.generateSuggestions("abot");
        long suggestionCount = suggestions.lines().count();

        Assertions.assertTrue(suggestionCount <= 10, "No more than 10 suggestions produced");
    }

}