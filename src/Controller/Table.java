package Controller;

public class Table {

    private String c3, c2, c4;
    private int c1;

    public Table(int c1, String c2, String c3, String c4) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    public String getC3() {
        return c3;
    }

    public int getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC4() {
        return c4;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }
}
