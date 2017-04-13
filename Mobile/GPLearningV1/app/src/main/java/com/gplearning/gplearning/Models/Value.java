package com.gplearning.gplearning.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "value")
public class Value {
    @Id
    private Long id;
    private String quote;

    public Value() {
    }

    @Generated(hash = 412347199)
    public Value(Long id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Long getId() {
        return this.id;
    }

    public String getQuote() {
        return this.quote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
