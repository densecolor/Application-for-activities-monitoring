/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

/**
 *
 * A person who carry out activities in a project
 *
 * @author 21503816
 */
public class ProjectMember extends Staff {

    public ProjectMember(String smFirstName, String smLastName, String skCode, String skName) {
        super(smFirstName, smLastName, skCode, skName);
    }

    @Override
    public String toString() {
        return "Project Member : " + "staffCode=" + super.getStaffCode() + ", First Name=" + super.getSmFirstName() + ", Last Name=" + super.getSmLastName();
    }

}
