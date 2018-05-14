/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

import java.util.Objects;

/**
 *
 * Business unit of the society
 * 
 * @author 21503816
 */
public class BusinessUnit {

    private final String unitCode;
    private final String unitName;

    public BusinessUnit(String unitCode, String unitName) {
        this.unitCode = unitCode;
        this.unitName = unitName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.unitCode);
        hash = 37 * hash + Objects.hashCode(this.unitName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BusinessUnit other = (BusinessUnit) obj;
        if (!Objects.equals(this.unitCode, other.unitCode)) {
            return false;
        }
        return Objects.equals(this.unitName, other.unitName);
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public String getUnitName() {
        return this.unitName;
    }

    @Override
    public String toString() {
        return "BusinessUnit : " + unitCode + ", unitName=" + unitName;
    }
    
    

}
