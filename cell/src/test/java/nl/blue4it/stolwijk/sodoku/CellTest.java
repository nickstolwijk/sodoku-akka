package nl.blue4it.stolwijk.sodoku;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

public class CellTest {

	@Test
	public void cellShouldKnowState() {
		Cell cell = new Cell();
		assertThat(cell.getState(), is(State.UNKNOWN));
	}
}
