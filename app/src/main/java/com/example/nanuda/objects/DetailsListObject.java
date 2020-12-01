package com.example.nanuda.objects;

/**
 * Object defining which user should pay which user how much in Balances.
 */
public class DetailsListObject {
    private String owerName;
    private String oweeName;
    private String owedAmount;

    public DetailsListObject(String owerName, String oweeName, String owedAmount) {
        this.owerName = owerName;
        this.oweeName = oweeName;
        this.owedAmount = owedAmount;
    }

    public String getOwerName() {
        return owerName;
    }

    public String getOweeName() {
        return oweeName;
    }

    public String getOwedAmount() {
        return owedAmount;
    }

    public void setOwerName(String owerName) {
        this.owerName = owerName;
    }

    public void setOweeName(String oweeName) {
        this.oweeName = oweeName;
    }

    public void setOwedAmount(String owedAmount) {
        this.owedAmount = owedAmount;
    }
}
