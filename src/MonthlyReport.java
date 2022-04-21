public class MonthlyReport {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;

    public MonthlyReport(String item_name, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = item_name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isIsExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }
}