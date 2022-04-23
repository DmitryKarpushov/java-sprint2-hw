import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReportReader parser = new ReportReader();
        Statistics statistics = new Statistics();

        printMenu();

        while (true) {

            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    parser.readMonthFile();
                    break;
                case 2:
                    parser.readFileYear();
                    break;
                case 3:
                    statistics.comparisonOfReports();
                    break;
                case 4:
                    statistics.statisticsMonth();
                    break;
                case 5:
                    statistics.statisticsYear();
                    break;
                case 6:
                    printMenu();
                    break;
                case 7:
                    System.out.println("Выход из приложения!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Введена неверная команда!");
                    printMenu();
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать? Выберите действия : ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Вывести меню");
        System.out.println("7 - Выйти из приложения");
    }
}

