package ir.delaramsharifi.entity;

import java.sql.Timestamp;
import java.util.Date;

public class TblAccountInformation {

    private Integer id;
    private Integer customerId;
    private Integer accountTypeId;
    private Integer cashAmount;
    private Integer withdrewAmount;
    private Integer depositAmount;
    private Timestamp withdrewDate;
    private Timestamp depositDate;
    private String comment;

    public TblAccountInformation() {
    }

    public TblAccountInformation(Integer id, Integer customerId, Integer accountTypeId, Integer cashAmount, Integer withdrewAmount, Integer depositAmount, Timestamp withdrewDate, Timestamp depositDate, String comment) {
        this.id = id;
        this.customerId = customerId;
        this.accountTypeId = accountTypeId;
        this.cashAmount = cashAmount;
        this.withdrewAmount = withdrewAmount;
        this.depositAmount = depositAmount;
        this.withdrewDate = withdrewDate;
        this.depositDate = depositDate;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Integer cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getWithdrewAmount() {
        return withdrewAmount;
    }

    public void setWithdrewAmount(Integer withdrewAmount) {
        this.withdrewAmount = withdrewAmount;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Timestamp getWithdrewDate() {
        return withdrewDate;
    }

    public void setWithdrewDate(Timestamp withdrewDate) {
        this.withdrewDate = withdrewDate;
    }

    public Timestamp getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Timestamp depositDate) {
        this.depositDate = depositDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return " AccountInformation is :" +
                ", customerId=" + customerId +
                ", accountTypeId=" + accountTypeId +
                ", cashAmount=" + cashAmount +
                ", withdrewAmount=" + withdrewAmount +
                ", depositAmount=" + depositAmount +
                ", withdrewDate=" + withdrewDate +
                ", depositDate=" + depositDate +
                ", comment='" + comment ;
    }
}
