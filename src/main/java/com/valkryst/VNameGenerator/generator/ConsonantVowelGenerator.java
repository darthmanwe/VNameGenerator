package com.valkryst.VNameGenerator.generator;

import java.util.concurrent.ThreadLocalRandom;

public final class ConsonantVowelGenerator extends NameGenerator {
    /** The consonants. */
    private final String[] consonants;
    /** The vowels. */
    private final String[] vowels;

    /**
     * Constructs a new ConsonantVowelGenerator.
     *
     * @param consonants
     *          The consonants.
     *
     * @param vowels
     *          The vowels.
     *
     * @throws IllegalArgumentException
     *          If the lists of consonants or vowels are empty or null.
     */
    public ConsonantVowelGenerator(final String[] consonants, final String[] vowels) {
        // Ensure lists aren't empty:
        if (consonants == null) {
            throw new IllegalArgumentException("The array of consonants is null.");
        }

        if (consonants.length == 0) {
            throw new IllegalArgumentException("The array of consonants is empty");
        }

        if (vowels == null) {
            throw new IllegalArgumentException("The array of vowels is null.");
        }

        if (vowels.length == 0) {
            throw new IllegalArgumentException("The array of vowels is empty");
        }

        this.consonants = consonants;
        this.vowels = vowels;
    }

    @Override
    public String generateName(int length) {
        if (length < 2) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();
        final ThreadLocalRandom rand = ThreadLocalRandom.current();

        while (sb.length() < length) {
            if (length % 2 == 0) {
                sb.append(vowels[rand.nextInt(vowels.length)]);
            } else {
                sb.append(consonants[rand.nextInt(consonants.length)]);
            }
        }

        if (sb.length() > length) {
            sb.deleteCharAt(sb.length() - (sb.length() - length));
        }

        return capitalizeFirstCharacter(sb.toString());
    }
}
