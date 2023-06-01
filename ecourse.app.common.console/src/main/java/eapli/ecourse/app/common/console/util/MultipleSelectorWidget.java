package eapli.ecourse.app.common.console.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;

public class MultipleSelectorWidget<T> extends ListWidget<T> {
  private int option = -1;

  public MultipleSelectorWidget(String header, Collection<T> source, Visitor<T> visitor) {
    super(header, source, visitor);
  }

  public Iterable<T> selectElements() {
    List<T> selected = new ArrayList<>();

    while (selectedOption() != 0 && source.size() > 0) {
      show();
      if (selectedOption() != 0) {
        final T s = selectedElement();
        source.remove(s);
        selected.add(s);
      }
    }

    return selected;
  }

  @Override
  public void show() {
    super.show();
    System.out.println("\n0. Confirm");
    this.option = Console.readOption(1, size(), 0);
  }

  /**
   *
   * @return -1 if the user has not yet made a selection 0 if the user
   *         selected "exit" a positive number corresponding to the list index
   *         of source if the user selected an item
   */
  public int selectedOption() {
    return this.option;
  }

  public T selectedElement() {
    switch (this.option) {
      case -1:
      case 0:
        return null;
      default:
        return fromIndex();
    }
  }

  private T fromIndex() {
    int idx = 0;
    T elem = null;
    final Iterator<T> it = source.iterator();
    while (idx < this.option) {
      elem = it.next();
      idx++;
    }
    return elem;
  }
}
