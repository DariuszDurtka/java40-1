import java.io.IOException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);

      boolean running = true;
      while (running) {
        System.out.println("\nWybierz opcję:");
        System.out.println("1 - Dodaj studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.println("3 - Zakończ program");
        System.out.print("Twój wybór: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
          case 1:
            System.out.print("Podaj imię studenta: ");
            String name = scanner.nextLine();

            System.out.print("Podaj nazwisko studenta: ");
            String lastName = scanner.nextLine();

            int age = 0;
            boolean validAge = false;
            while (!validAge) {
              System.out.print("Podaj wiek studenta: ");
              try {
                age = Integer.parseInt(scanner.nextLine());
                validAge = true;
              } catch (NumberFormatException e) {
                System.out.println("Wiek musi być liczbą. Spróbuj ponownie.");
              }
            }

            String birthDate = "";
            boolean validDate = false;
            while (!validDate) {
              System.out.print("Podaj datę urodzenia (DD-MM-YYYY): ");
              birthDate = scanner.nextLine();
              try {
                String[] dateParts = birthDate.split("-");
                if (dateParts.length == 3) {
                  int year = Integer.parseInt(dateParts[2]);
                  if (year >= 1900 && year <= 2025) {
                    validDate = true;
                  } else {
                    System.out.println("Rok musi być w przedziale 1900-2025. Spróbuj ponownie.");
                  }
                } else {
                  System.out.println("Błędny format daty. Spróbuj ponownie.");
                }
              } catch (NumberFormatException e) {
                System.out.println("Błędny format daty. Spróbuj ponownie.");
              }
            }

            s.addStudent(new Student(name, lastName, age, birthDate));
            System.out.println(" Dodano studenta.");
            break;

          case 2:
            var students = s.getStudents();
            System.out.println(" Lista studentów:");
            for (Student current : students) {
              System.out.println(current.toString());
            }
            break;

          case 3:
            running = false;
            System.out.println(" Zamykam program.");
            break;

          default:
            System.out.println("Nieznana opcja, spróbuj ponownie.");
            break;
        }
      }

      scanner.close();

    } catch (IOException e) {
      System.out.println(" Wystąpił błąd podczas obsługi pliku.");
    }
  }
}
