package eapli.ecourse.app.common.console.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;

public class MultipleSelectorWidget<T> {
  private String header;
  private Iterable<T> source;
  private Visitor<T> visitor;

  public MultipleSelectorWidget(String header, Iterable<T> source, Visitor<T> visitor) {
    this.header = header;
    this.source = source;
    this.visitor = visitor;
  }

  public Iterable<T> selectElements() {
    List<T> sourceList = new ArrayList<T>((Collection<T>) source);
    SelectWidget<T> selector = new SelectWidget<>(header, sourceList, visitor);

    List<T> selected = new ArrayList<>();

    while (selector.selectedOption() != 0 && sourceList.size() > 0) {
      selector = new SelectWidget<>(header, sourceList, visitor);
      selector.show();
      if (selector.selectedOption() != 0) {
        final T s = selector.selectedElement();
        sourceList.remove(s);
        selected.add(s);
      }
    }

    return selected;
  }
}
