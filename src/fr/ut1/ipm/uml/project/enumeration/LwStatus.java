/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.enumeration;

/**
 *
 * An enum for the status of a writa line
 * 
 * @author 21503816
 */
public enum LwStatus {
    
  PENDING ("Pending"),
  CONFIRMED ("Confirmed"),
  NOTCONFIRMED ("Not confirmed");

  private final String name;

  LwStatus(String name){
    this.name = name;
  }

    @Override
    public String toString() {
        return this.name;
    }
  
  
    
}
