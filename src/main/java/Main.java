import java.io.IOException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);

      System.out.println("Dodaj nowego studenta. Wprowadź 'exit', aby zakończyć.");
      while (true) {
        System.out.print("Imię: ");
        String name = scanner.nextLine();
        if ("exit".equalsIgnoreCase(name)) {
          break;
        }

        System.out.print("Wiek: ");
        int age;
        try {
          age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("Wiek musi być liczbą. Spróbuj ponownie.");
          continue;
        }

        s.addStudent(new Student(name, age));
      }

      System.out.println("\nLista studentów:");
      var students = s.getStudents();
      for (Student current : students) {
        System.out.println(current.ToString());
      }
    } catch (IOException e) {
      System.out.println("Wystąpił błąd wejścia/wyjścia: " + e.getMessage());
    }
  }
}
