package eapli.ecourse.app.common.console.util;

import java.util.ArrayList;
import java.util.List;

import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;

public class MultipleSelector<T> {
  private String header;
  private Iterable<T> source;
  private Visitor<T> visitor;

  public MultipleSelector(String header, Iterable<T> source, Visitor<T> visitor) {
    this.header = header;
    this.source = source;
    this.visitor = visitor;
  }

  final SelectWidget<T> selector = new SelectWidget<>(header, source, visitor);

  public Iterable<T> selectElements() {
    List<T> selected = new ArrayList<>();
    List<T> sourceList = (ArrayList<T>) source;

    while (selector.selectedOption() != 0 && sourceList.size() > 0) {
      selector.show();
      final T s = selector.selectedElement();
      sourceList.remove(s);
      selected.add(s);
    }

    return selected;
  }
}
