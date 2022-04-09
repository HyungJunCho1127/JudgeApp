package com.company;

public class Interface {
    InterfaceController interfaceController;

    public Interface(){

    }

    public void start(){
        interfaceController = new InterfaceController();
        System.out.println("Judge App Prototype");
        interfaceController.startOptions();

    }
}
