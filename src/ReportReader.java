import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class ReportReader {
    private String[] month = new String[]{"01", "02", "03"};

    private boolean readYear;
    private boolean readMonth;
    Scanner scanner = new Scanner(System.in);

    public List<YearlyReport> readFileYear() {
        List<YearlyReport> yearlyReports = new ArrayList<>();
        if (readYear) {
            System.out.println("Мы уже считали годовой отчет!");
            return null;
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
                yearlyReports.add(new YearlyReport(Integer.parseInt(arrayCSV[0]), Integer.parseInt(arrayCSV[1]), Boolean.parseBoolean(arrayCSV[2])));
            }
            System.out.println("Мы считали годовой отчет!");
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось считать годовой отчет");
        }
        readYear = true;
        return yearlyReports;
    }

    public HashMap<Integer, ArrayList<MonthlyReport>> readMonthFile() {
        HashMap<Integer, ArrayList<MonthlyReport>> monthlyReport = new HashMap<>();
        if (readMonth) {
            System.out.println("Месячные отчеты уже считаны!");
            return null;
        }
        for (String actualMonth : month) {
            String separator = File.separator;
            String url = "." + separator + "resources" + separator + "m.2021" + actualMonth + ".csv";
            try {
                scanner = new Scanner(new File(url).toPath());
                scanner.useDelimiter(System.getProperty("line.separator"));
                ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
                scanner.nextLine();
                while (scanner.hasNext()) {
                    final String s = scanner.nextLine();
                    String[] arrayCSV = s.split(",");
                    monthlyReports.add(new MonthlyReport(arrayCSV[0], Boolean.parseBoolean(arrayCSV[1]), Integer.parseInt(arrayCSV[2]), Integer.parseInt(arrayCSV[3])));
                }
                monthlyReport.put(Integer.parseInt(actualMonth), monthlyReports);
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Не удалось считать месячный отчет");
            }
        }
        System.out.println("Мы считали месячные отчеты! ");
        readMonth = true;
        return monthlyReport;
    }

}

