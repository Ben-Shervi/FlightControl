package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import flightControl.Control;

class Control_SaveAndLoadTest {

	@Test
	void test() throws FileNotFoundException {
		Control c1 = Test01.testControl();
		File f = new File("FileForTests");
		c1.save(f);
		Control c2 = new Control();
		c2.load(f);
		assertEquals(c1.toString(), c2.toString());
	}

}
