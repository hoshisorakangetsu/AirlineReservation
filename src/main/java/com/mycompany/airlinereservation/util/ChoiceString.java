package com.mycompany.airlinereservation.util;

// wrapper class for string so that it can be used in the getChoice function, and I don't need to overload the getChoice function to accept String array as they essentially does the same thing
public class ChoiceString implements Choicer {
    private String str;

    // no no-arg constructor for this class as this is a wrapper class for other class
    // so just throw an error if anyone attempts to call this without param
    ChoiceString() throws NoNoArgConstructorException {
        throw new NoNoArgConstructorException();
    }
    
    ChoiceString(String str) {
        this.str = str;
    }

    @Override
    public String toChoiceString() {
        return this.str;
    }
}

class NoNoArgConstructorException extends Exception {
    NoNoArgConstructorException() {
        super("This class's no-arg constructor does not have any functionalities");
    }
}
