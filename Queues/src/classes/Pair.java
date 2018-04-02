package classes;

public class Pair {
    private int first; //first member of pair
    private int second; //second member of pair
    private int third; //departure time

    public Pair(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setThird(int third) {
    	this.third = third;
    }
    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
    public int getThird() {
    	return third;
    }
    public String toString() {
		return first + "," + second; 
    	
    }
}

