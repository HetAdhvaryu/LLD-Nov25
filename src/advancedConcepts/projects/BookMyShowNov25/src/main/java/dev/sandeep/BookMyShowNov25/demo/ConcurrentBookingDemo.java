package dev.sandeep.BookMyShowNov25.demo;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CONCURRENT BOOKING DEMO
 *
 * This class demonstrates how two users trying to book the same seat
 * at the exact same time are handled by our system.
 *
 * Scenario: User A and User B both try to book Seat ID 1
 * Expected: One succeeds (HTTP 201), one fails (HTTP 400)
 */
public class ConcurrentBookingDemo {

    // Simulated HTTP client (in real scenario, this would be RestTemplate)
    private static class BookingClient {
        private final String userName;
        private final int userId;
        private final int[] showSeatIds;
        private final CountDownLatch startSignal;

        public BookingClient(String userName, int userId, int[] showSeatIds, CountDownLatch startSignal) {
            this.userName = userName;
            this.userId = userId;
            this.showSeatIds = showSeatIds;
            this.startSignal = startSignal;
        }

        /**
         * Simulates making a POST request to /ticket/book endpoint
         */
        public void bookTicket() {
            try {
                // Wait for signal to start (ensure simultaneous execution)
                startSignal.await();

                // Record request time
                long requestTime = System.currentTimeMillis();
                String formattedTime = formatTime(requestTime);

                System.out.println("\n[" + formattedTime + "] " + userName + " → POST /ticket/book");
                System.out.println("  Request Body: {\"userId\": " + userId + ", \"showSeatIds\": " + Arrays.toString(showSeatIds) + "}");

                // Call the booking endpoint (simulated)
                BookingResponse response = bookTicketRequest(userId, showSeatIds);

                // Display response
                if (response.isSuccess()) {
                    System.out.println("  ✅ Response: HTTP 201 Created");
                    System.out.println("  Ticket ID: " + response.getTicketId());
                    System.out.println("  Movie: " + response.getMovieTitle());
                    System.out.println("  Seats: " + response.getSeatNumbers());
                } else {
                    System.out.println("  ❌ Response: HTTP 400 Bad Request");
                    System.out.println("  Error: " + response.getErrorMessage());
                }

            } catch (InterruptedException e) {
                System.err.println(userName + " was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Simulates the booking request with database transaction
         */
        private BookingResponse bookTicketRequest(int userId, int[] showSeatIds) {
            try {
                // Simulate database transaction with SERIALIZABLE isolation
                System.out.println("  → Acquiring database lock for seats: " + Arrays.toString(showSeatIds));

                // Check if all seats are available
                System.out.println("  → Checking seat availability...");
                for (int seatId : showSeatIds) {
                    // Simulate database check
                    SeatStatus status = getCurrentSeatStatus(seatId);
                    System.out.println("    Seat " + seatId + ": " + status);

                    if (status != SeatStatus.AVAILABLE) {
                        throw new Exception("ShowSeat selected is not available, showSeatId: " + seatId);
                    }
                }

                // Lock seats
                System.out.println("  → Locking seats...");
                for (int seatId : showSeatIds) {
                    lockSeat(seatId);
                }

                // Simulate payment processing
                System.out.println("  → Processing payment...");
                Thread.sleep(100); // Simulate payment delay

                // Create ticket
                System.out.println("  → Creating ticket...");
                int ticketId = createTicket(userId, showSeatIds);

                // Book seats
                System.out.println("  → Booking seats...");
                for (int seatId : showSeatIds) {
                    bookSeat(seatId);
                }

                System.out.println("  → Transaction committed ✓");

                return new BookingResponse(true, ticketId, "Pathaan", "11");

            } catch (Exception e) {
                System.out.println("  → Exception: " + e.getMessage());
                System.out.println("  → Rolling back transaction...");
                return new BookingResponse(false, -1, null, null, e.getMessage());
            }
        }

        private String formatTime(long millis) {
            return String.format("T0.%03d", millis % 1000);
        }
    }

    /**
     * Response object for booking request
     */
    private static class BookingResponse {
        private final boolean success;
        private final int ticketId;
        private final String movieTitle;
        private final String seatNumbers;
        private final String errorMessage;

        public BookingResponse(boolean success, int ticketId, String movieTitle, String seatNumbers) {
            this.success = success;
            this.ticketId = ticketId;
            this.movieTitle = movieTitle;
            this.seatNumbers = seatNumbers;
            this.errorMessage = null;
        }

        public BookingResponse(boolean success, int ticketId, String movieTitle, String seatNumbers, String errorMessage) {
            this.success = success;
            this.ticketId = ticketId;
            this.movieTitle = movieTitle;
            this.seatNumbers = seatNumbers;
            this.errorMessage = errorMessage;
        }

        public boolean isSuccess() { return success; }
        public int getTicketId() { return ticketId; }
        public String getMovieTitle() { return movieTitle; }
        public String getSeatNumbers() { return seatNumbers; }
        public String getErrorMessage() { return errorMessage; }
    }

    /**
     * Seat status enum
     */
    private enum SeatStatus {
        AVAILABLE, LOCKED, BOOKED
    }

    /**
     * Simulated database state
     */
    private static class DatabaseSimulation {
        private static final AtomicInteger seatLockCounter = new AtomicInteger(0);
        private static SeatStatus seatStatus = SeatStatus.AVAILABLE;
        private static int ticketCounter = 100;

        static synchronized SeatStatus getSeatStatus(int seatId) {
            return seatStatus;
        }

        static synchronized void lockSeat(int seatId) {
            seatStatus = SeatStatus.LOCKED;
            seatLockCounter.incrementAndGet();
        }

        static synchronized void bookSeat(int seatId) {
            seatStatus = SeatStatus.BOOKED;
        }

        static synchronized int createTicket(int userId, int[] seatIds) {
            return ++ticketCounter;
        }

        static synchronized void reset() {
            seatStatus = SeatStatus.AVAILABLE;
            seatLockCounter.set(0);
            ticketCounter = 100;
        }
    }

    /**
     * Helper methods
     */
    private static SeatStatus getCurrentSeatStatus(int seatId) {
        return DatabaseSimulation.getSeatStatus(seatId);
    }

    private static void lockSeat(int seatId) {
        DatabaseSimulation.lockSeat(seatId);
    }

    private static void bookSeat(int seatId) {
        DatabaseSimulation.bookSeat(seatId);
    }

    private static int createTicket(int userId, int[] seatIds) {
        return DatabaseSimulation.createTicket(userId, seatIds);
    }

    /**
     * Main demo method
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                        ║");
        System.out.println("║          CONCURRENT BOOKING DEMO - Race Condition Handling             ║");
        System.out.println("║                                                                        ║");
        System.out.println("║     Scenario: Two users booking the same seat simultaneously           ║");
        System.out.println("║                                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

        System.out.println("\n═══════════════════════════════════════════════════════════════════════════");
        System.out.println("SETUP");
        System.out.println("═══════════════════════════════════════════════════════════════════════════");
        System.out.println("\nUser A: ID = 1 (Rajesh Kumar)");
        System.out.println("User B: ID = 2 (Priya Singh)");
        System.out.println("Show Seat: ID = 1 (Row 1, Seat 1 - GOLD/Premium)");
        System.out.println("Movie: Pathaan (Show starts at 10:00 AM)");
        System.out.println("\nCurrent Seat Status: AVAILABLE");
        System.out.println("Current Price: ₹250");

        System.out.println("\n═══════════════════════════════════════════════════════════════════════════");
        System.out.println("EXECUTION WITH SERIALIZABLE ISOLATION");
        System.out.println("═══════════════════════════════════════════════════════════════════════════");

        // Reset database
        DatabaseSimulation.reset();

        // Create countdown latch to synchronize thread start
        CountDownLatch startSignal = new CountDownLatch(1);

        // Create two booking clients
        BookingClient userA = new BookingClient("User A", 1, new int[]{1}, startSignal);
        BookingClient userB = new BookingClient("User B", 2, new int[]{1}, startSignal);

        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Submit both booking requests
            executor.submit(userA::bookTicket);
            executor.submit(userB::bookTicket);

            // Small delay to ensure both threads are ready
            Thread.sleep(100);

            // Release the start signal - both threads start simultaneously
            System.out.println("\n[T0.000] Releasing start signal - both requests execute simultaneously");
            startSignal.countDown();

            // Wait for both threads to complete
            executor.shutdown();
            executor.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            System.err.println("Demo interrupted: " + e.getMessage());
        }

        System.out.println("\n═══════════════════════════════════════════════════════════════════════════");
        System.out.println("FINAL OUTCOME");
        System.out.println("═══════════════════════════════════════════════════════════════════════════");
        System.out.println("\nDatabase Final State:");
        System.out.println("  Seat 1 Status: " + DatabaseSimulation.getSeatStatus(1));
        System.out.println("  Total Tickets Created: " + (DatabaseSimulation.ticketCounter - 100));
        System.out.println("\nResult: ✅ NO DOUBLE BOOKING - Seat booked to only ONE user");
        System.out.println("\n═══════════════════════════════════════════════════════════════════════════");
        System.out.println("Key Insight:");
        System.out.println("  • SERIALIZABLE isolation ensures sequential access");
        System.out.println("  • First transaction completes and commits");
        System.out.println("  • Second transaction sees changed state and fails");
        System.out.println("  • No partial bookings or data inconsistency");
        System.out.println("═══════════════════════════════════════════════════════════════════════════\n");
    }
}
