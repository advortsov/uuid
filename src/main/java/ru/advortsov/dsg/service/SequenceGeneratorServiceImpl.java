package ru.advortsov.dsg.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Distributed Sequence Generator that generates 64-bit IDs based on the concepts outlined in the Twitter snowflake service:
 * https://github.com/twitter/snowflake/tree/snowflake-2010
 * <p>
 * Epoch timestamp in milliseconds precision - 41 bits.
 * The maximum timestamp that can be represented using 41 bits is 241 - 1, or 2199023255551,
 * which comes out to be Wednesday, September 7, 2039 3:47:35.551 PM. That gives us 69 years with respect to a custom epoch.
 * Node ID - 10 bits. This gives us 1024 nodes/machines.
 * Local counter per machine - 12 bits. The counter’s max value would be 4095.
 * The remaining 1-bit is the signed bit and it is always set to 0.
 */
@Component
@Scope("singleton")
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    private static final int UNUSED_BITS = 1; // Sign bit, Unused (always set to 0)
    private static final int EPOCH_BITS = 41;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    // Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
    private static final long CUSTOM_EPOCH = 1420070400000L;

    private final int nodeId;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    // Create SequenceGenerator with a nodeId
    public SequenceGeneratorServiceImpl(@Value("${generator.nodeId}") int nodeId) {
        if (nodeId == 0 || nodeId > maxNodeId) {
            throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, maxNodeId));
        }
        this.nodeId = nodeId;
    }

    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                // Sequence Exhausted, wait till next millisecond.
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS);
        id |= (nodeId << SEQUENCE_BITS);
        id |= sequence;
        return id;
    }

    // Get current timestamp in milliseconds, adjust for the custom epoch.
    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    // Block and wait till next millisecond
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}