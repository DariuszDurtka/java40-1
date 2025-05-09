import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);
    var b = new BufferedWriter(f);
    b.append(student.toString());
    b.newLine();
    b.close();
  }

  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();
    var f = new FileReader("db.txt");
    var reader = new BufferedReader(f);
    String line;
    while ((line = reader.readLine()) != null) {
      ret.add(Student.parse(line));
    }
    reader.close();
    return ret;
  }

  // Metoda wyszukująca wszystkich studentów o podanym imieniu
  public Collection<Student> findStudentsByName(String name) throws IOException {
    Collection<Student> students = getStudents();
    Collection<Student> result = new ArrayList<>();
    for (Student st : students) {
      if (st.getName().equalsIgnoreCase(name)) {
        result.add(st);
      }
    }
    return result;
  }

  // Metoda usuwająca studenta (wszystkie wystąpienia) po imieniu i nazwisku
  public boolean removeStudentByNameAndLastName(String name, String lastName) throws IOException {
    Collection<Student> students = getStudents();
    boolean removed = false;
    Collection<Student> newList = new ArrayList<>();

    for (Student st : students) {
      if (st.getName().equalsIgnoreCase(name) && st.getLastName().equalsIgnoreCase(lastName)) {
        removed = true;
      } else {
        newList.add(st);
      }
    }

    BufferedWriter bw = new BufferedWriter(new FileWriter("db.txt", false));
    for (Student st : newList) {
      bw.write(st.toString());
      bw.newLine();
    }
    bw.close();

    return removed;
  }

  // Metoda aktualizująca wiek studenta po imieniu i nazwisku
  public boolean updateStudentAge(String name, String lastName, int newAge) throws IOException {
    ArrayList<Student> students = new ArrayList<>(getStudents());
    boolean updated = false;
    for (int i = 0; i < students.size(); i++) {
      Student st = students.get(i);
      if (st.getName().equalsIgnoreCase(name) && st.getLastName().equalsIgnoreCase(lastName)) {
        Student updatedStudent = new Student(st.getName(), st.getLastName(), newAge, st.getBirthDate());
        students.set(i, updatedStudent);
        updated = true;
      }
    }
    if (updated) {
      BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", false));
      for (Student st : students) {
        writer.write(st.toString());
        writer.newLine();
      }
      writer.close();
    }
    return updated;
  }
}
