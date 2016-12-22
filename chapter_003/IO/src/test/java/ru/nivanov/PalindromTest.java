package ru.nivanov;
import java.io.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * PalindromTest class.
 * @author nivanov.
 * @since
 * @version
 */
 public class PalindromTest {
   /**
	* Test for palindrom existence.
	* @throws IOException ..
	*/
	@Test
	public void whenPalikCorrectThenReturnOk() throws IOException {
		ByteArrayInputStream arr = new ByteArrayInputStream("kOmoK".getBytes());
		Palindrom test = new Palindrom();
		boolean result = test.isPalik(arr);
		assertThat(result, is(true));
	}
 }