package ru.advortsov.uuid.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * SequenceGeneratorServiceImplTest.
 *
 * @author Aleksandr_Dvortsov
 */
class SequenceGeneratorServiceImplTest {

    @Test
    void shouldFailIfNodeIdOutOfInterval() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SequenceGeneratorServiceImpl(4096);
        });
    }

}