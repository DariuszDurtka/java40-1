import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service { // Upewniamy się, że całość kodu jest w klasie

  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);
    var b = new BufferedWriter(f);
    b.append(student.toString());
    b.newLine();
    b.close();
  }

  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>(); // Określamy typ listy
    var f = new FileReader("db.txt");
    var reader = new BufferedReader(f);
    String line;

    while ((line = reader.readLine()) != null) {
      ret.add(Student.parse(line));
    }

    reader.close();
    return ret;
  }
}
