package ru.nivanov;
import java.io.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * DropAbuseTest class.
 * @author nivanov.
 * @since
 * @version
 */
 public class DropAbuseTest {
   /**
	* Test for abuse words filtering.
	* @throws IOException ..
	*/
	@Test
	public void whenAbuseFilterCorrectThenReturnResultString() throws IOException {
		DropAbuse filter = new DropAbuse();
		String[] abuse = {"Ass", "bad", "mad", "some"};
		InputStream in = new ByteArrayInputStream("a baD mAdman kickS SOME massive aSS".getBytes());
		OutputStream out = new ByteArrayOutputStream();
		filter.dropAbuses(in, out, abuse);
		assertThat(out.toString(), is("a  man kickS  mive "));
	}
	/**
	* Test2 for abuse words filtering.
	* @throws IOException ..
	*/
	@Test
	public void whenNoAbuseFoundThenReturnOriginalString() throws IOException {
		DropAbuse filter = new DropAbuse();
		String[] abuse = {"new", "few", "view"};
		InputStream in = new ByteArrayInputStream("a baD mAdman kickS SOME massive aSS".getBytes());
		OutputStream out = new ByteArrayOutputStream();
		filter.dropAbuses(in, out, abuse);
		assertThat(out.toString(), is("a baD mAdman kickS SOME massive aSS"));
	}
}