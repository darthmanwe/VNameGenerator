package com.valkryst.generator;

import com.valkryst.markov.MarkovChain;

import java.util.List;
import java.util.Optional;

public final class MarkovNameGenerator implements NameGenerator {
    /** The chain to use when generating names. */
    private final MarkovChain markovChain = new MarkovChain();

    /**
     * Constructs a MarkovNameGenerator.
     *
     * @param trainingNames
     *         The names to train the Markov Chain with.
     */
    public MarkovNameGenerator(final List<String> trainingNames) {
        // Ensure list isn't null:
        if (trainingNames == null) {
            throw new IllegalArgumentException("The list of training names is null.");
        }

        // Ensure list isn't empty:
        if (trainingNames.size() == 0) {
            throw new IllegalArgumentException("The list of training names is empty.");
        }

        markovChain.train(trainingNames);
    }

    @Override
    public String generateName(int length) {
        if (length == 0) {
            length = 2;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(markovChain.chooseRandomString()); // Begin the name with a random pair of two characters.

        for (int i = 2; i < length; ++i) {
            final Optional<Character> next = markovChain.chooseRandomCharacter(sb.substring(i - 2, i));

            if (next.isPresent()) {
                sb.append(next.get());
            } else {
                if (sb.length() != length) {
                    return generateName(length);
                } else {
                    break;
                }
            }
        }

        // Capitalize the first letter of the name:
        return sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1);
    }
}
