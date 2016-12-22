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
		BufferedReader arr = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("kOmoK".getBytes())));
		Palindrom test = new Palindrom();
		boolean result = test.isPalindrom(arr.readLine());
		assertThat(result, is(true));
	}
 }