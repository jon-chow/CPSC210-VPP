package model.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
	private Event e;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void eventTest() {
		assertEquals("Sensor open at door", e.getDescription());
		assertEquals(d, e.getDate());
	}

    @Test
    public void equalsTest() {
        assertFalse(e.equals(null));
        assertFalse(e.equals("String"));
    }

    @Test
    public void hashCodeTest() {
        assertNotEquals(0, e.hashCode());
    }

    @Test
	public void toStringTest() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}
}
