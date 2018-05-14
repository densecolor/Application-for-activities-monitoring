/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * A member of the society
 * 
 * @author 21503816
 */
public class Staff {

    private static final AtomicInteger count = new AtomicInteger(9999);
    private final String staffCode;
    private final String smFirstName;
    private final String smLastName;
    private final Skill skill;

    public Staff(String smFirstName, String smLastName, String skCode, String skName) {
        this.skill = new Skill(skCode, skName);
        this.staffCode = skill.getSkCode() + "-" + count.incrementAndGet();
        this.smFirstName = smFirstName;
        this.smLastName = smLastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.staffCode);
        hash = 59 * hash + Objects.hashCode(this.smFirstName);
        hash = 59 * hash + Objects.hashCode(this.smLastName);
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
        final Staff other = (Staff) obj;
        if (!Objects.equals(this.staffCode, other.staffCode)) {
            return false;
        }
        if (!Objects.equals(this.smFirstName, other.smFirstName)) {
            return false;
        }
        return Objects.equals(this.smLastName, other.smLastName);
    }

    public Skill getSkill() {
        return this.skill;
    }

    public String getStaffCode() {
        return this.staffCode;
    }

    public String getSmFirstName() {
        return this.smFirstName;
    }

    public String getSmLastName() {
        return this.smLastName;
    }

    @Override
    public String toString() {
        return "Staff : " + "staffCode=" + staffCode + ", First Name=" + smFirstName + ", Last Name=" + smLastName;
    }

}
