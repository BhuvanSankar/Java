package planner.test;

import planner.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * Basic tests for the {@link Allocator.allocate} method in the
 * {@link Allocator} implementation class.
 * 
 * Write your own junit4 tests for the method here.
 */
public class AllocatorTest {
	//Correct line separator for executing machine
	private final static String LINE_SEPARATOR = System.getProperty(
            "line.separator");
    // locations to test with
    private Location[] locations;
    // corridors to test with
    private Corridor[] corridors;
    // traffic objects to test with;
    private Traffic[] trafficRecords;
    // event objects to test with
    private Event[] events;
 // venue objects to test with
    private Venue[] venues;

    /**
     * This method is run by JUnit before each test to initialise instance
     * variables locations and corridors.
     */
    @Before
    public void setUp() throws Exception {	
    	
    	
    	
        // locations to test with
        locations = new Location[6];
        locations[0] = new Location("l0");
        locations[1] = new Location("l1");
        locations[2] = new Location("l2");
        locations[3] = new Location("l3");
        locations[4] = new Location("l4");
        locations[5] = new Location("l5");
        

        // corridors to test with
        corridors = new Corridor[7];
        corridors[0] = new Corridor(locations[0], locations[1], 15);
        corridors[1] = new Corridor(locations[1], locations[2], 15);
        corridors[2] = new Corridor(locations[2], locations[3], 30);
        corridors[3] = new Corridor(locations[3], locations[4], 20);
        corridors[4] = new Corridor(locations[4], locations[5], 20);
        corridors[5] = new Corridor(locations[1], locations[3], 25);
        corridors[6] = new Corridor(locations[2], locations[4], 20);
        
        
        

        // events to test with
        events = new Event[5];
        events[0] = new Event("e0", 10);
        events[1] = new Event("e1", 7);
        events[2] = new Event("e2", 5);
        events[3] = new Event("e2", 15);
        events[4] = new Event("e2", 20);
        
       
        
     // traffic records to test with
        trafficRecords = new Traffic[10];

        trafficRecords[0] = new Traffic();
        trafficRecords[0].updateTraffic(corridors[0], 10);
        trafficRecords[0].updateTraffic(corridors[1], 10);

        trafficRecords[1] = new Traffic();
        trafficRecords[1].updateTraffic(corridors[4], 4);
       

        trafficRecords[2] = new Traffic();       
        trafficRecords[2].updateTraffic(corridors[1], 10);

        trafficRecords[3] = new Traffic();
        trafficRecords[3].updateTraffic(corridors[3], 9);
        
        trafficRecords[4] = new Traffic();
        trafficRecords[4].updateTraffic(corridors[0], 9);
        trafficRecords[4].updateTraffic(corridors[2], 25);
        
        trafficRecords[5] = new Traffic();
        trafficRecords[5].updateTraffic(corridors[1], 9);
        trafficRecords[5].updateTraffic(corridors[3], 25);
        trafficRecords[5].updateTraffic(corridors[5], 15);
        
        trafficRecords[6] = new Traffic();
        trafficRecords[6].updateTraffic(corridors[4], 15);
        trafficRecords[6].updateTraffic(corridors[3], 25);
        trafficRecords[6].updateTraffic(corridors[6], 15);
        
        trafficRecords[7] = new Traffic();
        trafficRecords[7].updateTraffic(corridors[4], 10);
        trafficRecords[7].updateTraffic(corridors[2], 30);
        
        trafficRecords[8] = new Traffic();
        trafficRecords[8].updateTraffic(corridors[4], 15);
        
        trafficRecords[9] = new Traffic();
        trafficRecords[9].updateTraffic(corridors[6], 15);
        trafficRecords[9].updateTraffic(corridors[5], 25);

        
        
     // venue records to test with
        venues = new Venue[10];
        
        //venue parameters    
       
        Traffic capacityTraffic0 = trafficRecords[0];
        Traffic capacityTraffic1 = trafficRecords[1];
        Traffic capacityTraffic2 = trafficRecords[2];
        Traffic capacityTraffic3 = trafficRecords[3];
        Traffic capacityTraffic4 = trafficRecords[4];
        Traffic capacityTraffic5 = trafficRecords[5];
        Traffic capacityTraffic6 = trafficRecords[6];
        Traffic capacityTraffic7 = trafficRecords[7];
        Traffic capacityTraffic8 = trafficRecords[8];
        Traffic capacityTraffic9 = trafficRecords[9];
        
        
        
        venues[0] = new Venue("v0", 10, new Traffic
        		(capacityTraffic0));
        venues[1] = new Venue("v1", 4, new Traffic
        		(capacityTraffic1));
        venues[2] = new Venue("v2", 10, new Traffic
        		(capacityTraffic2));
        venues[3] = new Venue("v3", 9, new Traffic
        		(capacityTraffic3));
        venues[4] = new Venue("v4", 25, new Traffic
        		(capacityTraffic4));
        venues[5] = new Venue("v5", 30, new Traffic
        		(capacityTraffic5));
        venues[6] = new Venue("v6", 25, new Traffic
        		(capacityTraffic6));
        venues[7] = new Venue("v7", 30, new Traffic
        		(capacityTraffic7));
        venues[8] = new Venue("v8", 20, new Traffic
        		(capacityTraffic8));
        venues[9] = new Venue("v9", 25, new Traffic
        		(capacityTraffic9));  
    }     
    
    /**
     * Basic test: If the events list is empty, it will return null
     * If the events list is empty the allocations method return empty
     * set of maps and hence allocate method returns null
     */
    
    @Test(timeout = 5000)
    public void basicTest1() throws Exception{
    	List<Event> eventsList = new ArrayList<>();
    	List<Venue> venuesList = new ArrayList<>();
    	Map<Event, Venue> actualAllocation = new HashMap<>();
    	
    	//input parameters to method 
    	venuesList.add(venues[0]);
        venuesList.add(venues[1]);
        venuesList.add(venues[2]);
        venuesList.add(venues[3]);
        
       actualAllocation = Allocator.allocate(eventsList, venuesList);
       
       Assert.assertEquals(null, actualAllocation);       
    	
    }
    
    /**
     * Basic test: if there is no possible safe allocations
     * then the allocations method should return an empty set.
     * And therefore, allocate method should return null
     */
    
    @Test(timeout = 5000)
    public void basicTest2() throws Exception{
    	List<Event> eventsList = new ArrayList<>();
    	List<Venue> venuesList = new ArrayList<>();
    	Map<Event, Venue> actualAllocation = new HashMap<>();
    	
    	
    	eventsList.add(events[0]);
        eventsList.add(events[1]);
    	
        venuesList.add(venues[0]);
        venuesList.add(venues[1]);
        venuesList.add(venues[2]);
        
       actualAllocation = Allocator.allocate(eventsList, venuesList);
       
       Assert.assertEquals(null, actualAllocation);       
    	
    }
    
    /**
     * Elapsed Time Test: elapsed time of this test must be 
     * <=5s when the inputs are atmost 5 events and 10 venues
     */
    
    @Test(timeout = 5000)
    public void basicTest3() throws Exception{
    	List<Event> eventsList = new ArrayList<>();
    	List<Venue> venuesList = new ArrayList<>();
    	Map<Event, Venue> actualAllocation = new HashMap<>();
    	
    	
    	eventsList.add(events[0]);
        eventsList.add(events[1]);
        eventsList.add(events[2]);
        eventsList.add(events[3]);
        eventsList.add(events[4]);
        
        venuesList.add(venues[0]);
        venuesList.add(venues[1]);
        venuesList.add(venues[2]);
        venuesList.add(venues[3]);
        venuesList.add(venues[4]);
        venuesList.add(venues[5]);
        venuesList.add(venues[6]);
        venuesList.add(venues[7]);
        venuesList.add(venues[8]);
        venuesList.add(venues[9]);
        
       actualAllocation = Allocator.allocate(eventsList, venuesList);
       
            
    	
    }
    
    
    

}
