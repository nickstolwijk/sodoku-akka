package nl.blue4it.stolwijk.sodoku;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

public class CellTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void aNewCellShouldThrowIllegalArgumentOnToSmallANumberOfPossibleNumbers() {
        Cell.withPossibleNumbers(0);
    }

    @Test
    public void aNewCellShouldReturnAllNumbersAsPossibleOutcome() {
        Cell cell = Cell.withPossibleNumbers(6);

        assertThat(cell.getState(), is(State.UNKNOWN));
        assertThat(cell.getPossibleNumbers(), containsInAnyOrder(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void aNewCellShouldReturnAllNumbersNotTakenAway() {
        Cell cell = Cell.withPossibleNumbers(6);
        cell.takeAway(1);

        assertThat(cell.getState(), is(State.UNKNOWN));
        assertThat(cell.getPossibleNumbers(), containsInAnyOrder(2, 3, 4, 5, 6));
    }

    @Test
    public void aNewCellShouldReturnStateKnownIfOnlyASingleNumberRemains() {
        Cell cell = Cell.withPossibleNumbers(2);
        cell.takeAway(1);
        
        assertThat(cell.getState(), is(State.KNOWN));
        assertThat(cell.getPossibleNumbers(), containsInAnyOrder(2));
    }
    
    @Test
    public void aNewCellShouldRefuseToRemoveTheLastNumber() {
        Cell cell = Cell.withPossibleNumbers(2);
        cell.takeAway(1);
        cell.takeAway(2);
        
        assertThat(cell.getState(), is(State.KNOWN));
        assertThat(cell.getPossibleNumbers(), containsInAnyOrder(2));
    }
}
