package advancedConcepts.designPatterns.observer.subscriber;

import advancedConcepts.designPatterns.observer.AmazonOrderService;
import advancedConcepts.designPatterns.observer.OrderPlacedSubscriber;

public class InvoiceGenerator implements OrderPlacedSubscriber {

    public InvoiceGenerator() {
        // as soon as the object of this class is created, it will automatically subscribe to the publisher
        AmazonOrderService.getInstance().addOrderPlacedSubscriber(this);
    }

    @Override
    public void orderPlaceAction() {
        System.out.println("Generate invoice for customer");
    }
}
