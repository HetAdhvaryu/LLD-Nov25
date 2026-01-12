package advancedConcepts.designPatterns.observer.subscriber;

import advancedConcepts.designPatterns.observer.AmazonOrderService;
import advancedConcepts.designPatterns.observer.OrderPlacedSubscriber;

public class NotificationService implements OrderPlacedSubscriber {

    public NotificationService() {
        //AmazonOrderService.getInstance() => returns object [obj] of AmazonOrderService
        // obj.addOrderPlacedSubscriber(this) => 'this' is the object being created in constructor,
        // or current object, which is being added as a subscriber in the publisher's list

        // as soon as the object of this class is created, it will automatically subscribe to the publisher
        AmazonOrderService.getInstance().addOrderPlacedSubscriber(this);
    }

    @Override
    public void orderPlaceAction() {
        System.out.println("Send SMS to customer");
        System.out.println("Send email to customer");
    }
}
