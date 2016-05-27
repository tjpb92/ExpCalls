package expcalls;

import java.sql.*;

/**
 * Fcalls is a class that describes an active calls.
 * @version May 2016.
 * @author Thierry Baribaud.
 */
public class Fcalls {

  private int cnum;
  private int cunum;
  private String Cname;
  private String Ctel;
  private String Caddress;
  private String Caddress2;
  private String Caccess;
  private String Cposcode;
  private String City;
  private String Csympt;
  private String Cnumber4;
  private int cc6num;
  private Timestamp Cdate;
  private String Ctime;
  private Timestamp Cdate2;
  private String Ctime2;
  private String Corp;
  private String Cnumber5;

  public int getCnum() {
    return cnum;
    }

  public int getCunum() {
    return cunum;
    }

  public Timestamp getCdate() {
    return Cdate;
    }

  public String getCname() {
    return Cname;
    }

  public String getCtel() {
    return Ctel;
    }

  public String getCaddress() {
    return Caddress;
    }

  public String getCaddress2() {
    return Caddress2;
    }

  public String getCaccess() {
    return Caccess;
    }

  public String getCposcode() {
    return Cposcode;
    }

  public String getCity() {
    return City;
    }

  public String getCsympt() {
    return Csympt;
    }

  public String getCtime() {
    return Ctime;
    }

  public String getCtime2() {
    return Ctime2;
    }

  public String getCorp() {
    return Corp;
    }

  public String getCnumber5() {
    return Cnumber5;
    }

  public String getCnumber4() {
    return Cnumber4;
    }

  public int getCc6num() {
    return cc6num;
    }

  public Timestamp getCdate2() {
    return Cdate2;
    }

  public void setCnum(int cnum) {
    this.cnum = cnum;
    }

  public void setCunum(int cunum) {
    this.cunum = cunum;
    }

  public void setCdate(Timestamp Cdate) {
    this.Cdate = Cdate;
    }

  public void setCtel(String Ctel) {
    this.Ctel = (Ctel != null)?Ctel.trim():null;
    }

  public void setCname(String Cname) {
    this.Cname = (Cname != null)?Cname.trim():null;
    }

  public void setCaddress(String Caddress) {
    this.Caddress = (Caddress != null)?Caddress.trim():null;
    }

  public void setCaddress2(String Caddress2) {
    this.Caddress2 = (Caddress2 != null)?Caddress2.trim():null;
    }

  public void setCaccess(String Caccess) {
    this.Caccess = (Caccess != null)?Caccess.trim():null;
    }

  public void setCposcode(String Cposcode) {
    this.Cposcode = (Cposcode != null)?Cposcode.trim():null;
    }

  public void setCity(String City) {
    this.City = (City != null)?City.trim():null;
    }

  public void setCsympt(String Csympt) {
    this.Csympt = (Csympt != null)?Csympt.trim():null;
    }

  public void setCtime(String Ctime) {
    this.Ctime = (Ctime != null)?Ctime.trim():null;
    }

  public void setCtime2(String Ctime2) {
    this.Ctime2 = (Ctime2 != null)?Ctime2.trim():null;
    }

  public void setCorp(String Corp) {
    this.Corp = (Corp != null)?Corp.trim():null;
    }

  public void setCnumber5(String Cnumber5) {
    this.Cnumber5 = (Cnumber5 != null)?Cnumber5.trim():null;
    }

  public void setCnumber4(String Cnumber4) {
    this.Cnumber4 = (Cnumber4 != null)?Cnumber4.trim():null;
    }

  public void setCc6num(int cc6num) {
    this.cc6num = cc6num;
    }

  public void setCdate2(Timestamp Cdate2) {
    this.Cdate2 = Cdate2;
    }

  @Override
  public String toString() {
    return this.getClass().getName() +
           " : {cnum=" + cnum + 
           ", cunum=" + cunum + 
           ", Cname=" + Cname +
           ", Ctel=" + Ctel +
           ", Caddress=" + Caddress +
           ", Caddress2=" + Caddress2 + 
           ", Caccess=" + Caccess + 
           ", Cposcode=" + Cposcode + 
           ", City=" + City + 
           ", Csympt=" + Csympt + 
           ", Cnumber4=" + Cnumber4 + 
           ", cc6num=" + cc6num +
           ", Cdate=" + Cdate +
           ", Ctime=" + Ctime + 
           ", Cdate2=" + Cdate2 +
           ", Ctime2=" + Ctime2 + 
           ", Corp=" + Corp + 
           ", Cnumber5=" + Cnumber5 + 
           "}";
  }
}
