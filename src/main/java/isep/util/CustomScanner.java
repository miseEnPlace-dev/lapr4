package isep.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Source:
 * https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
public class CustomScanner {
  BufferedReader br;
  StringTokenizer st;
  File file;

  public CustomScanner(String filename) throws FileNotFoundException {
    file = new File(filename);
    if (!file.exists())
      throw new FileNotFoundException(String.format("The file %s does not exist!", filename));
    br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public String nextLine() {
    String str = "";
    try {
      str = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }

  public boolean hasNextLine() {
    boolean hasNextLine = false;
    try {
      hasNextLine = br.ready();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return hasNextLine;
  }

  int nextInt() {
    return Integer.parseInt(next());
  }

  long nextLong() {
    return Long.parseLong(next());
  }

  double nextDouble() {
    return Double.parseDouble(next());
  }

  public void close() {
    try {
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
