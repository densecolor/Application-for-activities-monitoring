/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.report;

import fr.ut1.ipm.uml.project.data.Activity;
import fr.ut1.ipm.uml.project.data.Intervention;
import fr.ut1.ipm.uml.project.enumeration.LwStatus;
import fr.ut1.ipm.uml.project.data.Project;
import fr.ut1.ipm.uml.project.data.ProjectMember;
import java.util.Objects;

/**
 *
 * A line of a WRITA
 *
 * @author 21503816
 */
public class LineWrita {

    private final String lwCode;
    private String lwStatus;
    private Integer nbHoursCarriedOut;
    private Project project;
    private final ProjectMember projectMember;
    private Intervention intervention;
    private Activity activity;

    public LineWrita(Integer nbHoursCarriedOut, Project project, ProjectMember projectMember, Intervention intervention, Activity activity) {
        this.lwStatus = LwStatus.PENDING.toString();
        this.nbHoursCarriedOut = nbHoursCarriedOut;
        this.project = project;
        this.projectMember = projectMember;
        this.intervention = intervention;
        this.activity = activity;
        this.lwCode = this.activity.getActCode() + this.projectMember.getStaffCode().substring(3) + "-" + this.project.getProjCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.lwCode);
        hash = 67 * hash + Objects.hashCode(this.nbHoursCarriedOut);
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
        final LineWrita other = (LineWrita) obj;
        if (!Objects.equals(this.lwCode, other.lwCode)) {
            return false;
        }
        return Objects.equals(this.nbHoursCarriedOut, other.nbHoursCarriedOut);
    }

    /**
     * Method to consult a line of WRITA
     *
     * @return a line of writa
     */
    public String consultLineWrita() {
        return this.activity.getActCode() + "        " + this.project.getProjCode() + "         " + this.intervention.getInterDesc() + "                          " + this.lwStatus + "            " + this.nbHoursCarriedOut;
    }

    /**
     * Method to consult a line of WRITA for a project manager (more
     * name and surname of the member)
     *
     * @return a line of writa
     */
    public String consultLineWritaForProjectManager() {
        return this.activity.getActCode() + " " + this.project.getProjCode()
                + this.projectMember.getSmFirstName() + " " + this.projectMember.getSmLastName() + " "
                + this.intervention.getInterDesc() + " " + this.lwStatus + " " + this.nbHoursCarriedOut;
    }

    /**
     * Method to modify a line of WRITA
     *
     * @param nbHoursCarriedOut a number hours carried out
     * @param project a project
     * @param intervention an intervention
     * @param activity an activity
     */
    public void modifyLineWrita(Integer nbHoursCarriedOut, Project project, Intervention intervention, Activity activity) {
        this.nbHoursCarriedOut = nbHoursCarriedOut;
        this.project = project;
        this.intervention = intervention;
        this.activity = activity;
    }

    /**
     * Method to change thte status of a line of WRITA
     *
     * @param status enum of status
     */
    public void changeStatus(LwStatus status) {
        this.lwStatus = status.toString();
    }

    public String getLwStatus() {
        return this.lwStatus;
    }

    public Integer getNbHoursCarriedOut() {
        return this.nbHoursCarriedOut;
    }

    public Project getProject() {
        return this.project;
    }

    public ProjectMember getProjectMember() {
        return this.projectMember;
    }

    public Intervention getIntervention() {
        return this.intervention;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public String getLwCode() {
        return this.lwCode;
    }

    @Override
    public String toString() {
        return this.consultLineWrita();
    }

}
