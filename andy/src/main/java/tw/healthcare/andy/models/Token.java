package tw.healthcare.andy.models;

import java.util.Date;

/**
 * Created by yingzhang on 27/9/16.
 */

public class Token {
    private String name;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
