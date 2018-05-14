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
 * Intervention about activity
 * 
 * @author 21503816
 */
public class Intervention {

    private static final AtomicInteger count = new AtomicInteger(9999);
    private final String interCode;
    private final String interDesc;
    private final Activity activity;

    public Intervention(String interDesc, String ActDesc, String ActName) {
        this.activity = new Activity(ActDesc, ActName);
        this.interCode = this.activity.getActCode() + count.incrementAndGet();
        this.interDesc = interDesc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.interCode);
        hash = 53 * hash + Objects.hashCode(this.interDesc);
        hash = 53 * hash + Objects.hashCode(this.activity);
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
        final Intervention other = (Intervention) obj;
        if (!Objects.equals(this.interCode, other.interCode)) {
            return false;
        }
        if (!Objects.equals(this.interDesc, other.interDesc)) {
            return false;
        }
        return Objects.equals(this.activity, other.activity);
    }

    public Activity getActivity() {
        return this.activity;
    }

    public String getInterCode() {
        return this.interCode;
    }

    public String getInterDesc() {
        return this.interDesc;
    }

}
