package advancedConcepts.designPatterns.observer;

/***
 * Any class that would implement this interface would be a
 * subscriber to the order place event
 */
public interface OrderPlacedSubscriber {
    /***
     * This method will be the execution/action that needs to be done by the subscriber
     * once it listens to the actual event
     * For example - Notification Service will send notification to customers when the order place event occurs
     */
    void orderPlaceAction();
}
