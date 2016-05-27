package expcalls;

import java.sql.*;

/**
 * Fcomplmt is a class that describes a call complement.
 *
 * @version May 2016.
 * @author Thierry Baribaud.
 */
public class Fcomplmt {

    private int c6num;
    private int c6int2;
    private String C6alpha1;
    private String C6alpha2;
    private String C6name;
    private String C6access;
    private String C6city;
    private String C6tel;
    private String C6alpha3;
    private String C6alpha4;
    private String C6alpha5;
    private String C6alpha6;
    private String C6alpha7;
    private int c6int1;
    private Timestamp C6date;
    private Timestamp C6date1;
    private int c6int3;
    private int c6int4;
    private int c6onum;

    public int getC6num() {
        return c6num;
    }

    public int getC6int2() {
        return c6int2;
    }

    public Timestamp getC6date() {
        return C6date;
    }

    public String getC6alpha1() {
        return C6alpha1;
    }

    public String getC6alpha2() {
        return C6alpha2;
    }

    public String getC6name() {
        return C6name;
    }

    public String getC6access() {
        return C6access;
    }

    public String getC6city() {
        return C6city;
    }

    public String getC6tel() {
        return C6tel;
    }

    public String getC6alpha3() {
        return C6alpha3;
    }

    public String getC6alpha4() {
        return C6alpha4;
    }

    public String getC6alpha5() {
        return C6alpha5;
    }

    public String getC6alpha6() {
        return C6alpha6;
    }

    public String getC6alpha7() {
        return C6alpha7;
    }

    public int getC6int1() {
        return c6int1;
    }

    public Timestamp getC6date1() {
        return C6date1;
    }

    public int getC6int3() {
        return c6int3;
    }

    public int getC6int4() {
        return c6int4;
    }

    public int getC6onum() {
        return c6onum;
    }

    public void setC6num(int c6num) {
        this.c6num = c6num;
    }

    public void setC6int2(int c6int2) {
        this.c6int2 = c6int2;
    }

    public void setC6date(Timestamp C6date) {
        this.C6date = C6date;
    }

    public void setC6alpha2(String C6alpha2) {
        this.C6alpha2 = (C6alpha2 != null) ? C6alpha2.trim() : null;
    }

    public void setC6alpha1(String C6alpha1) {
        this.C6alpha1 = (C6alpha1 != null) ? C6alpha1.trim() : null;
    }

    public void setC6name(String C6name) {
        this.C6name = (C6name != null) ? C6name.trim() : null;
    }

    public void setC6access(String C6access) {
        this.C6access = (C6access != null) ? C6access.trim() : null;
    }

    public void setC6city(String C6city) {
        this.C6city = (C6city != null) ? C6city.trim() : null;
    }

    public void setC6tel(String C6tel) {
        this.C6tel = (C6tel != null) ? C6tel.trim() : null;
    }

    public void setC6alpha3(String C6alpha3) {
        this.C6alpha3 = (C6alpha3 != null) ? C6alpha3.trim() : null;
    }

    public void setC6alpha4(String C6alpha4) {
        this.C6alpha4 = (C6alpha4 != null) ? C6alpha4.trim() : null;
    }

    public void setC6alpha5(String C6alpha5) {
        this.C6alpha5 = (C6alpha5 != null) ? C6alpha5.trim() : null;
    }

    public void setC6alpha6(String C6alpha6) {
        this.C6alpha6 = (C6alpha6 != null) ? C6alpha6.trim() : null;
    }

    public void setC6alpha7(String C6alpha7) {
        this.C6alpha7 = (C6alpha7 != null) ? C6alpha7.trim() : null;
    }

    public void setC6int1(int c6int1) {
        this.c6int1 = c6int1;
    }

    public void setC6date1(Timestamp C6date1) {
        this.C6date1 = C6date1;
    }

    public void setC6int3(int c6int3) {
        this.c6int3 = c6int3;
    }

    public void setC6int4(int c6int4) {
        this.c6int4 = c6int4;
    }

    public void setC6onum(int c6onum) {
        this.c6onum = c6onum;
    }

    @Override
    public String toString() {
        return this.getClass().getName()
                + " : {c6num=" + c6num
                + ", c6int2=" + c6int2
                + ", C6alpha1=" + C6alpha1
                + ", C6alpha2=" + C6alpha2
                + ", C6name=" + C6name
                + ", C6access=" + C6access
                + ", C6city=" + C6city
                + ", C6tel=" + C6tel
                + ", C6alpha3=" + C6alpha3
                + ", C6alpha4=" + C6alpha4
                + ", C6alpha5=" + C6alpha5
                + ", C6alpha6=" + C6alpha6
                + ", C6alpha7=" + C6alpha7
                + ", c6int1=" + c6int1
                + ", C6date=" + C6date
                + ", C6date1=" + C6date1
                + ", c6int3=" + c6int3
                + ", c6int4=" + c6int4
                + ", c6onum=" + c6onum
                + "}";
    }
}
