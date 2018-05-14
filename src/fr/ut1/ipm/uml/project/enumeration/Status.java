/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.enumeration;

/**
 *
 * An enum for the status of a WRITA or a summary
 *
 * @author 21503816
 */
public enum Status {

    SENT("Sent"),
    SAVED("Saved");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
