package ru.nivanov.start;
import ru.nivanov.models.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * ConsoleInputTest class.
 * @author nivanov.
 * @since
 * @version
 */

public class ConsoleInputTest {
	/**
	* Test for console input.
	*
	*/
	@Test
	public void whenAddItemThenReturnId() {
		System.setIn(new ByteArrayInputStream("123".getBytes()));
			ConsoleInput test = new ConsoleInput();
			assertThat(test.ask("enter 123\n"), is("123"));
	}
}