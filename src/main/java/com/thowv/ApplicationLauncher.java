package com.thowv;

public class ApplicationLauncher {

    // Why we need a separate launcher: https://stackoverflow.com/questions/52653836/maven-shade-javafx-runtime-components-are-missing
    public static void main(String[] args) {
        Application.main(args);
    }
}
