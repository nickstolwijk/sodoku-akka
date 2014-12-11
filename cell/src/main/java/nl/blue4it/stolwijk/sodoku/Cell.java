package nl.blue4it.stolwijk.sodoku;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

@ToString
@EqualsAndHashCode
public class Cell {
    private final Set<Integer> possibleNumbers;

    public static Cell withPossibleNumbers(int possibleNumbers) {
        return new Cell(possibleNumbers);
    }

    private Cell(int maxNumberOfNumbers) {
        super();
        Preconditions.checkArgument(maxNumberOfNumbers > 0, "non-positive value: %s", maxNumberOfNumbers);

        this.possibleNumbers = IntStream.rangeClosed(1, maxNumberOfNumbers).boxed().collect(Collectors.toSet());
    }

    public State getState() {
        if (getPossibleNumbers().size() == 1) {
            return State.KNOWN;
        } else {
            return State.UNKNOWN;
        }
    }

    public Set<Integer> getPossibleNumbers() {
        return ImmutableSet.copyOf(possibleNumbers);
    }

    public void takeAway(int takeAway) {
        if (!getState().equals(State.KNOWN)) {
            possibleNumbers.remove(takeAway);
        }
    }

}
