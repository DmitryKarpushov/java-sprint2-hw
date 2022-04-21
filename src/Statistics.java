import java.util.*;
import java.util.logging.Logger;

public class Statistics {
    private static Logger log = Logger.getLogger(Statistics.class.getName());
    private List<YearlyReport> yearlyReport = ReadFile.getList();
    private Map<Integer, ArrayList<MonthlyReport>> monthlyReport = ReadFile.getMap();
    private ArrayList<String> titleMonth = month();

    private ArrayList<String> month() {
        ArrayList<String> arrayListMonth = new ArrayList<>();
        arrayListMonth.add("Январь");
        arrayListMonth.add("Февраль");
        arrayListMonth.add("Март");
        return arrayListMonth;
    }

    public void statisticsMonth() {
        if (yearlyReport.isEmpty() || monthlyReport.isEmpty()) {
            System.out.println("Файлы еще не считаны");
        } else {
            Iterator<Map.Entry<Integer, ArrayList<MonthlyReport>>> entries = monthlyReport.entrySet().iterator();
            String profitableProduct = "";
            String unprofitableProduct = "";
            int monthIncome;
            while (entries.hasNext()) {
                Map.Entry<Integer, ArrayList<MonthlyReport>> entriesMoth = entries.next();
                monthIncome = entriesMoth.getKey();

                int profitableSum = 0;
                int unprofitableSum = 0;

                for (int i = 0; i < entriesMoth.getValue().size(); i++) {
                    MonthlyReport Month = entriesMoth.getValue().get(i);
                    // log.info("TEST  Month.getQuantity()  = " + Month.getQuantity() );
                    // log.info("TEST Month.getSumOfOne()  = " + Month.getSumOfOne() );
                    int sum = Month.getQuantity() * Month.getSumOfOne();
                    if (!Month.isIsExpense() && sum > profitableSum) {
                        profitableSum = sum;
                        profitableProduct = Month.getItemName();
                    } else if (Month.isIsExpense() && sum > unprofitableSum) {
                        unprofitableSum = sum;
                        unprofitableProduct = Month.getItemName();
                    }
                }
                System.out.println("                       Итог за " + titleMonth.get(monthIncome - 1) + " месяц: ");
                System.out.println("Самый прибыльный товар " + profitableSum + " " + profitableProduct);
                System.out.println("Самый убыточный товар  " + unprofitableSum + " " + unprofitableProduct);
                System.out.println(" ");
            }
        }
        return;
    }

    public void statisticsYear() {
        if (yearlyReport.isEmpty() || monthlyReport.isEmpty()) {
            System.out.println("Файлы еще не считаны");
        } else {
            int profit = 0;
            int totalIncome = 0;
            int unProfit = 0;
            int totalConsumption = 0;
            int counter = 0;
            for (YearlyReport year : yearlyReport) {
                if (!year.isIsExpense()) {
                    profit += year.getAmount();
                    totalIncome += year.getAmount();
                } else {
                    unProfit += year.getAmount();
                    totalConsumption += year.getAmount();
                }
                // log.info("TEST  profit  = " + profit );
                // log.info("TEST unProfit  = " + unProfit );
                // log.info("TEST  profit  = " + totalIncome );
                // log.info("TEST unProfit  = " + consumptionTotal );
                counter++;
                while (counter == 2) {
                    System.out.println("Прибыль за " + titleMonth.get(year.getMonth() - 1) + " " + (profit - unProfit));
                    profit = 0;
                    unProfit = 0;
                    counter = 0;
                }
            }
            System.out.println("Средний расход за все месяцы в году : " + totalConsumption / (yearlyReport.size() / 2));
            System.out.println("Средний доход за все месяцы в году :  " + totalIncome / (yearlyReport.size() / 2));

        }
        return;
    }

    public void comparisonOfReports() {
        if (yearlyReport.isEmpty() || monthlyReport.isEmpty()) {
            System.out.println("Файлы еще не считаны! Выберите первую и вторую команду :");
        } else {
            Iterator<Map.Entry<Integer, ArrayList<MonthlyReport>>> entries = monthlyReport.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<Integer, ArrayList<MonthlyReport>> entriesMonth = entries.next();
                int highProfit = 0;
                int lowProfit = 0;
                for (int i = 0; i < entriesMonth.getValue().size(); i++) {
                    MonthlyReport monthlyReport = entriesMonth.getValue().get(i);
                    int sum = monthlyReport.getSumOfOne() * monthlyReport.getQuantity();
                    if (!monthlyReport.isIsExpense()) {
                        highProfit += sum;
                    } else {
                        lowProfit += sum;
                    }
                }
                int sum = highProfit - lowProfit;
                if (sumMonth(entriesMonth.getKey()) != sum) {
                    System.out.println("Ошибка в отчете за " + titleMonth.get(entriesMonth.getKey() - 1) + " месяц ");
                    return;
                }
            }
            System.out.println("Отчеты успешно сверены! ");
        }
        return;
    }

    private int sumMonth(int monthSum) {
        int sumProfit = 0;
        int sumUnProfit = 0;
        for (YearlyReport year : yearlyReport) {
            if (year.getMonth() == monthSum && year.isIsExpense()) {
                sumUnProfit += year.getAmount();
            } else if (year.getMonth() == monthSum && !year.isIsExpense()) {
                sumProfit += year.getAmount();
            }
        }
        return sumProfit - sumUnProfit;
    }

}