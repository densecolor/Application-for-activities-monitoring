/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

import java.util.Objects;

/**
 *
 * A skill owned by a staff member
 *
 * @author 21503816
 */
public class Skill {

    private final String skCode;
    private final String skName;

    public Skill(String skCode, String skName) {
        this.skCode = skCode;
        this.skName = skName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.skCode);
        hash = 97 * hash + Objects.hashCode(this.skName);
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
        final Skill other = (Skill) obj;
        if (!Objects.equals(this.skCode, other.skCode)) {
            return false;
        }
        return Objects.equals(this.skName, other.skName);
    }

    public String getSkCode() {
        return this.skCode;
    }

    public String getSkName() {
        return this.skName;
    }

    @Override
    public String toString() {
        return "Skill : " + "code=" + skCode + ", name=" + skName;
    }

}
