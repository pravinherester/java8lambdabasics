package org.java8action.FPTechniques;

public class TrainJourney {

    public int price;
    public TrainJourney onward;

    public TrainJourney(int price, TrainJourney trainJourney) {
        super();
        this.price = price;
        this.onward = trainJourney;
    }

    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null)
            return b;
        TrainJourney t = a;

        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;

    }
    
    static TrainJourney append(TrainJourney a, TrainJourney b){
        return a==null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    public static void main(String[] args) {
        TrainJourney a = new TrainJourney(10, null);
        System.out.println(a.onward);
        TrainJourney b = new TrainJourney(20, null);
        TrainJourney c = append(a, b);
        System.out.println(a.price);
        System.out.println(c.price);
        
        
        

    }
}
