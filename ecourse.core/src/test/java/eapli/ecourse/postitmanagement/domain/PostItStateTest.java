package eapli.ecourse.postitmanagement.domain;

import org.junit.Test;

import eapli.ecourse.postitmanagement.domain.PostItState.State;

public class PostItStateTest {

  @Test
  public void assertSimpleConstructorWorks() {
    PostItState s = new PostItState();
    assert (s != null);
    assert (s.isActive());
  }

  @Test
  public void assertStateConstructorWorks() {
    PostItState s = new PostItState(State.DELETED);
    assert (s != null);
    assert (s.isDeleted());
  }

  @Test
  public void assertIsDeletedWorks() {
    PostItState s = new PostItState(State.DELETED);
    assert (s.isDeleted());
  }

  @Test
  public void assertIsDeletedWorksWhenFalse() {
    PostItState s = new PostItState(State.ACTIVE);
    assert (!s.isDeleted());
  }

  @Test
  public void assertIsActiveWorks() {
    PostItState s = new PostItState(State.ACTIVE);
    assert (s.isActive());
  }

  @Test
  public void assertIsActiveWorksWhenFalse() {
    PostItState s = new PostItState(State.DELETED);
    assert (!s.isActive());
  }

  @Test
  public void assertEqualsWorks() {
    PostItState s1 = new PostItState(State.ACTIVE);
    PostItState s2 = new PostItState(State.ACTIVE);
    assert (s1.equals(s2));
  }

  @Test
  public void assertEqualsWorksWhenSameObject() {
    PostItState s1 = new PostItState(State.ACTIVE);
    assert (s1.equals(s1));
  }

  @Test
  public void assertEqualsWorksWhenDifferentObjects() {
    PostItState s1 = new PostItState(State.ACTIVE);
    PostItState s2 = new PostItState(State.DELETED);
    assert (!s1.equals(s2));
  }

  @Test
  public void assertGetHashCodeWorks() {
    PostItState s1 = new PostItState(State.ACTIVE);
    PostItState s2 = new PostItState(State.ACTIVE);
    assert (s1.getHashCode() == s2.getHashCode());
  }

  @Test
  public void assertToStringWorks() {
    PostItState s = new PostItState(State.ACTIVE);
    assert (s.toString() == "ACTIVE");
  }

}
