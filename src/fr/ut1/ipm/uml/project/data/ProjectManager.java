/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

import java.util.List;

/**
 *
 * A person who manage a project
 *
 * @author 21503816
 */
public class ProjectManager extends ProjectMember {

    public ProjectManager(String smFirstName, String smLastName, String skCode, String skName) {
        super(smFirstName, smLastName, skCode, skName);
    }

    /**
     * Method which return hours carried out for projects of a project manager
     *
     * @param listProject a list of project
     * @return Integer hours carried out
     */
    public Integer getNbHoursCarriedOutByMembers(List<Project> listProject) {
        int totalNbHoursCarriedOut = 0;
        for (Project project : listProject) {
            if (project.getProjectManager().getStaffCode().equals(this.getStaffCode())) {
                totalNbHoursCarriedOut += project.getNbHoursCarriedOutForProject();
            }
        }
        return totalNbHoursCarriedOut;
    }

    /**
     * Method which return hours expected for projects of a project manager
     *
     * @param listProject
     * @return Integer hours carried out
     */
    public Integer getNbHoursExpectedByMembers(List<Project> listProject) {
        int totalNbHoursExpected = 0;
        for (Project project : listProject) {
            if (project.getProjectManager().equals(this)) {
                totalNbHoursExpected += project.getExpectedHoursForProject();
            }
        }
        return totalNbHoursExpected;
    }

    @Override
    public String toString() {
        return "Project Manager : " + "staffCode=" + super.getStaffCode() + ", First Name=" + super.getSmFirstName() + ", Last Name=" + super.getSmLastName();
    }

}
