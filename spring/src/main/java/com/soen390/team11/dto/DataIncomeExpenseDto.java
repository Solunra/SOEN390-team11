package com.soen390.team11.dto;

public class DataIncomeExpenseDto {
    private String month;
    private String amount;

    public DataIncomeExpenseDto() {
    }

    public DataIncomeExpenseDto(String month, String amount) {
        this.month = month;
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public String getAmount() {
        return amount;
    }

    public void setMonth(Integer month) {
        switch(month) {
            case 1:
                this.month = "January";
                break;
            case 2:
                this.month = "February";
                break;
            case 3:
                this.month = "March";
                break;
            case 4:
                this.month = "April";
                break;
            case 5:
                this.month = "May";
                break;
            case 6:
                this.month = "June";
                break;
            case 7:
                this.month = "July";
                break;
            case 8:
                this.month = "August";
                break;
            case 9:
                this.month = "September";
                break;
            case 10:
                this.month = "October";
                break;
            case 11:
                this.month = "November";
                break;
            case 12:
                this.month = "December";
                break;
        }
    }

    public void setAmount(String amount) {
        this.amount =amount;
    }
}
