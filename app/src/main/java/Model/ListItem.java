package Model;

public class ListItem {

    public String expenseName;
    public String paidBy;

    public ListItem(String expenseName, String paidBy) {
        this.expenseName = expenseName;
        this.paidBy = paidBy;
    }
    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

}
