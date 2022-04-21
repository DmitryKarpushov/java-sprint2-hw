public class YearlyReport {

    private int month;
    private int amount;
    private boolean isExpense;

    public YearlyReport(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getMonth() {
        return month;
    }

    public boolean isIsExpense() {
        return isExpense;
    }

    public int getAmount() {
        return amount;
    }
}



















/*
    public void FileTest2(){
        try {
            File file = new File("resources/y.2021.csv");
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] words = line.split(",");
            System.out.println(Arrays.toString(words));

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

*/
