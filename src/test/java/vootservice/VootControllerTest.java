package vootservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VootControllerTest {

    private VootController subject;

    @Before
    public void before(){
        subject = new VootController();
    }
    @Test
    public void testMyGroups() throws Exception {

        assertNotNull(subject.myGroups());
    }
}
