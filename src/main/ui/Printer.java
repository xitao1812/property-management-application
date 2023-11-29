package ui;

import model.Event;
import model.EventLog;

import java.io.IOException;

public class Printer {

    public static void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
            System.out.println("\n");
        }
    }

}
