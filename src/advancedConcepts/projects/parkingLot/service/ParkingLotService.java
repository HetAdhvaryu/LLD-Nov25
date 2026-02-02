package advancedConcepts.projects.parkingLot.service;

import advancedConcepts.projects.parkingLot.model.*;
import advancedConcepts.projects.parkingLot.model.constants.BillStatus;
import advancedConcepts.projects.parkingLot.model.constants.ParkingSpotStatus;
import advancedConcepts.projects.parkingLot.model.constants.PaymentMode;
import advancedConcepts.projects.parkingLot.repository.*;
import advancedConcepts.projects.parkingLot.service.billingStrategy.BillCalculationStrategies;
import advancedConcepts.projects.parkingLot.service.billingStrategy.BillCalculationStrategy;
import advancedConcepts.projects.parkingLot.service.billingStrategy.BillCalculationStrategyFactory;
import advancedConcepts.projects.parkingLot.service.spotAllocationStrategy.SpotAllocationStrategy;
import advancedConcepts.projects.parkingLot.service.spotAllocationStrategy.SpotAllocationStrategyFactory;

import java.time.LocalDateTime;

public class ParkingLotService {

    private ParkingSpotRepository parkingSpotRepository;
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;
    private GateRepository gateRepository;
    private BillRepository billRepository;

    public ParkingLotService() {
        this.parkingSpotRepository = new ParkingSpotRepository();
        this.ticketRepository = new TicketRepository();
        this.parkingLotRepository = new ParkingLotRepository();
        this.gateRepository = new GateRepository();
        this.billRepository = new BillRepository();
    }

    public Ticket generateTicket(int parkingLotId, Vehicle vehicle, int gateId){
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);
        Gate gate = gateRepository.getGateById(gateId);
        SpotAllocationStrategy strategy = SpotAllocationStrategyFactory.getSpotAllocationStrategy();
        ParkingSpot parkingSpot = strategy.getSpotForVehicle(parkingLot, vehicle);

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        ticket.setEntryGate(gate);
        ticket.setEntryTime(LocalDateTime.now());

        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);
        parkingSpot.setVehicle(vehicle);
        parkingSpotRepository.save(parkingSpot);

        parkingLot.setCurrentOccupancy(parkingLot.getCurrentOccupancy() + 1);
        parkingLotRepository.save(parkingLot);

        return ticketRepository.save(ticket);
    }

    public Bill generateBill(int parkingLotId, int ticketId, int gateId){
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);
        Gate gate = gateRepository.getGateById(gateId);
        Ticket ticket = ticketRepository.getTicketById(ticketId);

        double occupancyRatio = parkingLot.getCurrentOccupancy()/parkingLot.getCapacity();
        BillCalculationStrategy strategy = BillCalculationStrategyFactory.getBillCalculationStrategy(BillCalculationStrategies.SIMPLE);
        double totalCost = strategy.calculateBillCost(ticket, occupancyRatio);

        Bill bill = new Bill();
        bill.setExitTime(LocalDateTime.now());
        bill.setAmount(totalCost);
        bill.setTicket(ticket);
        bill.setBillStatus(BillStatus.PAID);
        bill.setPaymentMode(PaymentMode.UPI);
        bill.setPaymentRefNumber("PAY12345");

        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.FREE);
        parkingSpot.setVehicle(null);
        parkingSpotRepository.save(parkingSpot);

        parkingLot.setCurrentOccupancy(parkingLot.getCurrentOccupancy() - 1);
        parkingLotRepository.save(parkingLot);

        return billRepository.save(bill);
    }
}
