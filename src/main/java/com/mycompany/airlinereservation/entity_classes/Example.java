package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.Choicer;

public class Example extends ExampleParent implements Choicer {

    public String toChoiceString() {
        return "Simple and short description of the object that can be displayed in a table";
    }
    
}
