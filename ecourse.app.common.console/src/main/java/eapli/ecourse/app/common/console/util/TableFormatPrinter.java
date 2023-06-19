package eapli.ecourse.app.common.console.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class TableFormatPrinter {
  // workaround for LinkedHashMap not working as expected
  private final ArrayList<Map.Entry<String, Integer>> columns;

  public TableFormatPrinter() {
    columns = new ArrayList<>();
  }

  public void addColumn(String column, Integer width) {
    Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(column, width);
    columns.add(entry);
  }

  public String getWord(Map.Entry<String, Integer> entry) {
    if (entry.getKey().length() > entry.getValue())
      return entry.getKey().substring(0, entry.getValue() - 3) + "…";
    return entry.getKey();
  }

  public String format() {
    StringBuilder expression = new StringBuilder();

    for (Map.Entry<String, Integer> entry : columns) {
      String s = String.format("%-" + entry.getValue() + "s", getWord(entry));
      expression.append(s);
    }

    return expression.toString();
  }
}
