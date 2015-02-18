package voot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VootControllerTest {

  private VootController subject;

  @Before
  public void before() {
    subject = new VootController();
  }

  @Mock
  private Principal principal;

  @Test
  public void testMyGroups() throws Exception {

    when(principal.getName()).thenReturn("foo");
    assertNotNull(subject.myGroups(principal));
  }
}
