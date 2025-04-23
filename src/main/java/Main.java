import java.io.IOException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);

      while (true) {
        System.out.println("\nWybierz opcję:");
        System.out.println("1 - Dodaj nowego studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.println("3 - Zakończ program");
        System.out.print("Twój wybór: ");

        String choice = scanner.nextLine();

        switch (choice) {
          case "1":
            System.out.print("Imię: ");
            String name = scanner.nextLine();

            System.out.print("Wiek: ");
            int age;
            try {
              age = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
              System.out.println("Wiek musi być liczbą. Spróbuj ponownie.");
              continue;
            }

            s.addStudent(new Student(name, age));
            System.out.println("Student został dodany.");
            break;

          case "2":
            System.out.println("\nLista studentów:");
            var students = s.getStudents();
            for (Student current : students) {
              System.out.println(current.ToString());
            }
            break;

          case "3":
            System.out.println("Kończenie programu. Do zobaczenia!");
            return;

          default:
            System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
            break;
        }
      }
    } catch (IOException e) {
      System.out.println("Wystąpił błąd wejścia/wyjścia: " + e.getMessage());
    }
  }
}
