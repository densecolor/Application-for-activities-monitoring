/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

/**
 *
 * An hour scheduled for an activity, a project and a member
 *
 * @author 21503816
 */
public class Schedule {

    private final Integer nbHoursScheduled;
    private final Activity activity;
    private final Project project;
    private final ProjectMember projectMember;

    public Schedule(Integer nbHoursScheduled, Activity activity, Project project, ProjectMember projectMember) {
        this.nbHoursScheduled = nbHoursScheduled;
        this.activity = activity;
        this.project = project;
        this.projectMember = projectMember;
    }

    public Integer getNbHoursScheduled() {
        return this.nbHoursScheduled;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Project getProject() {
        return this.project;
    }

    public ProjectMember getProjectMember() {
        return projectMember;
    }

}
