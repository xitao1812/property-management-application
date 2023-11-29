package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


//Code influenced by Alarm System application from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
//Represents a log of property list application event
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // MODIFIES: this
    // EFFECTS: construct an event log
    private EventLog() {
        events = new ArrayList<Event>();
    }



    // EFFECTS: returns instance of EventLog, creates it if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }


    // EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: returns events' iterator
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
