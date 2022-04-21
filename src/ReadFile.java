import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class ReadFile {
    private static Logger log = Logger.getLogger(ReadFile.class.getName());
    private String[] month = new String[]{"01", "02", "03"};
    private static HashMap<Integer, ArrayList<MonthlyReport>> MonthlyReport = new HashMap<>();
    private static List<YearlyReport> YearlyReport = new ArrayList<>();
    private boolean booleanYear;
    private boolean booleanMonth;
    private Scanner scanner;

    public ReadFile(Scanner scanner) {
        this.scanner = scanner;

    }

    public void readFileYear() {
        if (booleanYear) {
            System.out.println("Мы уже считали годовой отчет!");
            return;
        }
        String separator = File.separator;
        String file = "resources" + separator + "y.2021.csv";
        try {
            scanner = new Scanner(new File(file).toPath());
            scanner.useDelimiter(System.getProperty("line.separator"));
            scanner.nextLine();
            while (scanner.hasNext()) {
                final String s = scanner.nextLine();
                String[] arrayCSV = s.split(",");
                //
                //log.info("TEST 1 = " + arrayCSV[0] + " TEST 2 = " + arrayCSV[1] + " TEST 3 = " + arrayCSV[2]);
                YearlyReport.add(new YearlyReport(Integer.parseInt(arrayCSV[0]), Integer.parseInt(arrayCSV[1]), Boolean.parseBoolean(arrayCSV[2])));
            }
            System.out.println("Мы считали годовой отчет!");
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось считать годовой отчет");
        }
        booleanYear = true;
    }

    public void readMonthFile() {
        if (booleanMonth) {
            System.out.println("Месячные отчеты уже считаны!");
            return;
        }
        for (String actualMonth : month) {
            String separator = File.separator;
            String url = "." + separator + "resources" + separator + "m.2021" + actualMonth + ".csv";
            try {
                scanner = new Scanner(new File(url).toPath());
                scanner.useDelimiter(System.getProperty("line.separator"));
                ArrayList<MonthlyReport> monthList = new ArrayList<>();
                scanner.nextLine();
                while (scanner.hasNext()) {
                    final String s = scanner.nextLine();
                    String[] arrayCSV = s.split(",");
                    //log.info("TEST 1 = " + arrayCSV[0] + " TEST 2 = " + arrayCSV[1] + " TEST 3 = " + arrayCSV[2]);
                    monthList.add(new MonthlyReport(arrayCSV[0], Boolean.parseBoolean(arrayCSV[1]), Integer.parseInt(arrayCSV[2]), Integer.parseInt(arrayCSV[3])));
                }
                MonthlyReport.put(Integer.parseInt(actualMonth), monthList);
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Не удалось считать месячный отчет");
            }
        }
        System.out.println("Мы считали месячные отчеты! ");
        booleanMonth = true;
    }

    public static List<YearlyReport> getList() {
        return YearlyReport;
    }

    public static HashMap<Integer, ArrayList<MonthlyReport>> getMap() {
        return MonthlyReport;
    }
}
