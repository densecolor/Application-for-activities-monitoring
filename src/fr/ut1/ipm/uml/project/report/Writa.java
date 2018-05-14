/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.report;

import fr.ut1.ipm.uml.project.data.Activity;
import fr.ut1.ipm.uml.project.data.Intervention;
import fr.ut1.ipm.uml.project.data.Project;
import fr.ut1.ipm.uml.project.enumeration.Status;
import fr.ut1.ipm.uml.project.data.ProjectMember;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * A list of writa lines for a member of a project
 *
 * @author 21503816
 */
public class Writa {

    private final String wrCode;
    private Enum wrStatus;
    private final String wrNumWeek;
    private final String wrYear;
    private final ProjectMember projectMember;
    private final List<LineWrita> listLineWrita;

    public Writa(ProjectMember projectMember) {
        this.wrYear = this.getCurrentYear();
        this.wrNumWeek = this.getCurrentWeek();
        this.projectMember = projectMember;
        this.wrCode = this.getCurrentWeek() + this.getCurrentYear() + this.projectMember.getStaffCode();
        this.wrStatus = Status.SAVED;
        this.listLineWrita = new ArrayList();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.wrCode);
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
        final Writa other = (Writa) obj;
        return Objects.equals(this.wrCode, other.wrCode);
    }

    /**
     * Method to get the number of the current week
     *
     * @return un number of week
     */
    private String getCurrentWeek() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * Method to write a WRITA
     *
     * @param listLw a list of WRITA line
     */
    public void writeWrita(List<LineWrita> listLw) {
        for (LineWrita line : listLw) {
            this.addLineWrita(line);
        }
    }

    /**
     * Method to add a line to a WRITA (to update or writing)
     *
     * @param lw a line of WRITA
     */
    public void addLineWrita(LineWrita lw) {
        this.getListLineWrita().add(lw);
    }

    /**
     * Method to remove a line to a WRITA
     *
     * @param lw a line of WRITA
     */
    public void removeLineWrita(LineWrita lw) {
        this.getListLineWrita().remove(lw);
    }

    /**
     * Method to update a line of WRITA
     *
     * @param lw a line of WRITA
     * @param nbHoursCarriedOut a number hours carried out
     * @param project a project
     * @param intervention an intervention
     * @param activity an activity
     */
    public void updateWrita(LineWrita lw, Integer nbHoursCarriedOut, Project project, Intervention intervention, Activity activity) {
        lw.modifyLineWrita(nbHoursCarriedOut, project, intervention, activity);
    }

    /**
     * Method to consult a WRITA
     *
     * @return a WRITA
     */
    public String consultWrita() {
        String writa = "";
        writa += "Activity :    Project :      Description of intervention :       Status :        Weekly time (hours) :\n";
        Integer nbHours = 0;
        for (LineWrita line : this.listLineWrita) {
            writa += line + "\n";
            nbHours += line.getNbHoursCarriedOut();
        }
        writa += "\n";
        writa += "Total number of hours : " + nbHours;
        return writa;
    }

    /**
     * Method to change the status of a WRITA
     *
     * @param status enum of status
     */
    public void changeStatus(Status status) {
        this.wrStatus = status;
    }

    /**
     * Method to get the current year
     *
     * @return a year
     */
    private String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date());
    }

    public String getWrNumWeek() {
        return this.wrNumWeek;
    }

    public String getWrYear() {
        return this.wrYear;
    }

    public List<LineWrita> getListLineWrita() {
        return this.listLineWrita;
    }

    public String getWrCode() {
        return this.wrCode;
    }

    public String getWrStatus() {
        return this.wrStatus.toString();
    }

    public ProjectMember getProjectMember() {
        return this.projectMember;
    }

    @Override
    public String toString() {
        String writa = "*********************************************WEEKLY REPORT OF IT ACTIVITIES**************************************************\n";
        writa += "Name of " + this.getProjectMember() + "                   " + "Year : " + this.getCurrentYear() + "\n";
        writa += "Staff number : " + this.getProjectMember().getStaffCode() + "                                                                                 " + "Week n° : " + this.getWrNumWeek() + "\n \n";
        writa += this.consultWrita() + "\n";
        writa += "******************************************************************************************************************************";
        return writa;
    }

}
