package advancedConcepts.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

//publisher
// THIS CLASS SHOULD BE SINGLETON -
// as it maintains a list of subscriber objects, and multiple objects of this class can lead to mismanagement of subscribers
public class AmazonOrderService {
    private static AmazonOrderService instance;
    private List<OrderPlacedSubscriber> subscribers;

    private AmazonOrderService() {
        subscribers = new ArrayList<OrderPlacedSubscriber>();
    }

    public static AmazonOrderService getInstance() {
        // TODO: add double check locking
        if(instance == null) {
            instance = new AmazonOrderService();
        }
        return instance;
    }

    public void orderPlace(){
        //some order place logic
        //order is placed successfully
        //inform all the subscribers
        for(OrderPlacedSubscriber subscriber : subscribers){
            subscriber.orderPlaceAction();
        }
    }

    public void addOrderPlacedSubscriber(OrderPlacedSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeOrderPlacedSubscriber(OrderPlacedSubscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
