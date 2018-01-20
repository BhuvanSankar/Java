package planner;

//FAULTY IMPLEMENTATION

import java.util.*;

/**
 * Provides a method for finding a safe allocation of events to venues.
 */
public class Allocator {

    // variables used for testing different errors
    private static boolean[] ERROR = { false, false, false, false, false };
    // private static boolean[] ERROR = { true, false, false, false, false };
    // private static boolean[] ERROR = { false, true, false, false, false };
    // private static boolean[] ERROR = { false, false, true, false, false };
    // private static boolean[] ERROR = { false, false, false, true, false };
    // private static boolean[] ERROR = { false, false, false, false, true };

    /**
     * <p>
     * Returns a safe allocation of events to venues, if there is at least one
     * possible safe allocation, or null otherwise.
     * </p>
     * 
     * <p>
     * NOTE: What it means for an allocation of events to venues to be safe is
     * defined in the assignment handout.
     * </p>
     * 
     * @require events != null && venues != null && !events.contains(null) &&
     *          !venues.contains(null) && events does not contain duplicate
     *          events && venues does not contain duplicate venues.
     * @ensure Returns a safe allocation of events to venues, if there is at
     *         least one possible safe allocation, or null otherwise.
     */
    public static Map<Event, Venue> allocate(List<Event> events,
            List<Venue> venues) {
        // DO NOT MODIFY THE IMPLEMENTATION OF THIS METHOD
        Set<Map<Event, Venue>> allocations = allocations(new ArrayList<>(
                events), new ArrayList<>(venues));

        // Set<Map<Event, Venue>> allocations = allocations(events, venues);
        if (allocations.isEmpty()) {
            // returns null to signify that there is no possible safe allocation
            return null;
        } else {
            // returns one (any one) of the possible safe allocations
            return allocations.iterator().next();
        }
    }

    /**
     * Returns the set of all possible safe allocations of events to venues.
     * 
     * @require events != null && venues != null && !events.contains(null) &&
     *          !venues.contains(null) && events does not contain duplicate
     *          events && venues does not contain duplicate venues.
     * @ensure Returns the set of all possible safe allocations of events to
     *         venues. (Note: if there are no possible allocations, then this
     *         method should return an empty set of allocations.)
     */
    private static Set<Map<Event, Venue>> allocations(List<Event> events,
            List<Venue> venues) {
        // set of possible allocations
        Set<Map<Event, Venue>> result = new HashSet<>();

        /* BASE CASE: no more events to allocate */
        if (events.isEmpty()) {
            if (ERROR[4]) {
                // ERROR[4]:forgot to add the one valid allocation to the result
            } else {
                result.add(new HashMap<Event, Venue>());
            }
            return result;

        }

        /* RECURSIVE CASE: there is at least one more event to allocate. */
        // the event to be allocated next
        Event event = events.get(0);
        // the rest of the events to be allocated
        List<Event> remainingEvents = events.subList(1, events.size());
        for (int i = 0; i < venues.size(); i++) {
            // find possible safe allocations of event at the ith venue
            Venue venue = venues.get(i);
            if (venue.canHost(event) || ERROR[0]) {
                // ERROR[0]: we don't check if the venue can host the event
                if (!ERROR[2]) {
                    // ERROR[2]: we might allocate the same venue twice
                    venues.remove(i); // remove venue from available venues
                }
                if (ERROR[4] && remainingEvents.isEmpty()) {
                    // COMPENSATING FOR PART OF ERROR[4]
                    Map<Event, Venue> allocation = new HashMap<>();
                    allocation.put(event, venue);
                    result.add(allocation);
                }
                Set<Map<Event, Venue>> allocations = allocations(
                        remainingEvents, venues);
                for (Map<Event, Venue> allocation : allocations) {
                    allocation.put(event, venue);
                    if (safeTraffic(allocation)) {
                        result.add(allocation);
                    }
                }
                if (!ERROR[2]) {
                    // ERROR[2]: no need to put it back if we didn't take it out
                    venues.add(i, venue); // add venue back to available venues
                }
            }
        }
        if (ERROR[3]) {
            // ERROR[3]: incorrectly return null if there are no allocations
            if (result.isEmpty()) {
                return null;
            }
        }
        return result;
    }

    /**
     * Returns the traffic caused by the given allocation.
     * 
     * @requires allocation!=null && the keys in allocation are not null and
     *           each event in keySet of allocation maps to a non-null venue
     *           that can host that event.
     * @ensures returns the traffic caused by the given allocation.
     */
    private static Traffic getUsageOf(Map<Event, Venue> allocation) {
        Traffic result = new Traffic();
        for (Event event : allocation.keySet()) {
            Venue venue = allocation.get(event);
            result.addTraffic(venue.getTraffic(event));
        }
        return result;
    }

    /**
     * Returns true if the traffic in the given allocation is safe, and false
     * otherwise.
     * 
     * @requires allocation!=null && the keys in allocation are not null and
     *           each event in keySet of allocation maps to a non-null venue
     *           that can host that event.
     * @ensures returns whether or not the traffic in the given allocation is
     *          safe.
     */
    private static boolean safeTraffic(Map<Event, Venue> allocation) {
        Traffic traffic = getUsageOf(allocation);
        if (ERROR[1]) {
            // ERROR[1]: traffic is always assumed to be safe
            return true;
        } else {
            return traffic.isSafe();
        }
    }

}
