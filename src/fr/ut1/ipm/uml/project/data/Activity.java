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
 * Activity of a project
 *
 * @author 21503816
 */
public class Activity {

    private static final AtomicInteger count = new AtomicInteger(9999);
    private final String ActCode;
    private final String ActDesc;
    private final String ActName;

    public Activity(String ActDesc, String ActName) {
        this.ActCode = "A" + count.incrementAndGet();
        this.ActDesc = ActDesc;
        this.ActName = ActName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.ActCode);
        hash = 29 * hash + Objects.hashCode(this.ActDesc);
        hash = 29 * hash + Objects.hashCode(this.ActName);
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
        final Activity other = (Activity) obj;
        if (!Objects.equals(this.ActCode, other.ActCode)) {
            return false;
        }
        if (!Objects.equals(this.ActDesc, other.ActDesc)) {
            return false;
        }
        return Objects.equals(this.ActName, other.ActName);
    }

    public String getActCode() {
        return ActCode;
    }

    public String getActDesc() {
        return this.ActDesc;
    }

    public String getActName() {
        return this.ActName;
    }

    @Override
    public String toString() {
        return "Activity : " + "ActDesc=" + ActDesc + ", ActName=" + ActName;
    }

}
