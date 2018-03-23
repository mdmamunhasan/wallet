package com.mmnhsn.wallet.models;

/**
 * Created by mamun on 12/11/17.
 */

public class AccountTransactionEntity {
    private Long id = null;
    private String title = null;
    private Integer type = null;
    private Integer amount = null;
    private Long timestamp = null;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }
}
