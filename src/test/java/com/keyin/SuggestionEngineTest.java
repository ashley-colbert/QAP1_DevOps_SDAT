package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine;


    @Test
    public void testGenerateSuggestions() throws IOException {
        suggestionEngine = new SuggestionEngine();
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("abot").contains("about"));
    }
}
