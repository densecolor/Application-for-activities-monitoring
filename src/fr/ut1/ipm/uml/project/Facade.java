/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project;

import fr.ut1.ipm.uml.project.data.Project;
import fr.ut1.ipm.uml.project.data.Schedule;
import fr.ut1.ipm.uml.project.data.Skill;
import fr.ut1.ipm.uml.project.data.Intervention;
import fr.ut1.ipm.uml.project.data.Activity;
import fr.ut1.ipm.uml.project.enumeration.LwStatus;
import fr.ut1.ipm.uml.project.enumeration.Status;
import fr.ut1.ipm.uml.project.summary.Summary;
import fr.ut1.ipm.uml.project.data.ProjectManager;
import fr.ut1.ipm.uml.project.report.LineWrita;
import fr.ut1.ipm.uml.project.report.Writa;
import fr.ut1.ipm.uml.project.data.ProjectMember;
import fr.ut1.ipm.uml.dao.Dao;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Facade to call objects and methods of the information system
 *
 * @author 21503816
 */
public class Facade {

    private final List<Project> listProject;
    private final List<ProjectManager> listProjectManager;
    private final List<ProjectMember> listProjectMember;
    private final List<Skill> listSkill;
    private final List<LineWrita> listLineWrita;
    private final List<Activity> listActivity;
    private final List<Intervention> listIntervention;
    private final List<Schedule> listHoursSchedule;

    public Facade(Dao dao) throws IOException, ParseException {
        this.listProjectManager = dao.getListProjectManager();
        this.listProjectMember = dao.getListProjectMember();
        this.listSkill = dao.getListSkill();
        this.listActivity = dao.getListActivity();
        this.listIntervention = dao.getListIntervention();
        this.listHoursSchedule = dao.getListHoursScheduled();
        this.listProject = dao.getListProject();
        this.listLineWrita = dao.getListLineWrita();
        this.addLineWritaAndActivityToProject();
    }

    /**
     * Method to add lines of WRITA and activities to related project
     */
    private void addLineWritaAndActivityToProject() {
        this.listProject.forEach((Project project) -> {
            for (LineWrita line : this.listLineWrita) {
                if (project.equals(line.getProject())) {
                    project.addToListLw(line);
                    project.getMapActivity().put(line.getActivity(), this.getNbHoursExpectedForActivity(line.getActivity()));
                }
            }
        });
    }

    /**
     * Method to get hours scheduled for an activity
     *
     * @param activity an activity
     * @return hours scheduled
     */
    public Integer getNbHoursExpectedForActivity(Activity activity) {
        int nbHoursScheduled = 0;
        for (Schedule line : this.listHoursSchedule) {
            if (line.getActivity().equals(activity)) {
                nbHoursScheduled += line.getNbHoursScheduled();
            }
        }
        return nbHoursScheduled;
    }

    /**
     * Method to create a WRITA with the facade
     *
     * @return a writa
     */
    public Writa createWrita() {
        System.out.print("Craation of a WRITA for the ");
        ProjectMember projectMember1 = this.getListProjectMember().get(20);
        System.out.println(projectMember1 + "\n");
        Writa writaMember1 = new Writa(projectMember1);
        List<LineWrita> linesMember1 = new ArrayList();

        for (LineWrita line : this.getListLineWrita()) {
            if (line.getProjectMember().equals(projectMember1)) {
                linesMember1.add(line);
            }
        }

        writaMember1.writeWrita(linesMember1);
        System.out.println(writaMember1 + "\n");
        System.out.println("Sending the WRITA to the system\n");
        writaMember1.changeStatus(Status.SENT);

        return writaMember1;
    }

    /**
     * Method to check lines of WRITA by the project manager
     *
     * @param writa a writa
     * @return a project manager
     */
    public ProjectManager checkWritaByProjectManager(Writa writa) {
        System.out.print("Validation of a WRITA line by the project manager : ");
        ProjectManager projectManager1 = this.getListProjectManager().get(2);
        System.out.println(projectManager1 + "\n");

        for (LineWrita line : writa.getListLineWrita()) {
            if (line.getProject().getProjectManager().equals(projectManager1)) {
                line.changeStatus(LwStatus.CONFIRMED);
            }
        }
        System.out.print("Invalidation of a line by a project manager : ");
        ProjectManager projectManager2 = this.getListProjectManager().get(4);
        System.out.println(projectManager2 + "\n");

        for (LineWrita line : writa.getListLineWrita()) {
            if (line.getProject().getProjectManager().equals(projectManager2)) {
                line.changeStatus(LwStatus.NOTCONFIRMED);
            }
        }
        System.out.println("Display of WRITA after examination by a project manager :\n");
        System.out.println(writa + "\n");

        return projectManager2;
    }

    /**
     * Method to correct a WRITA by a project member
     *
     * @param writa a writa
     */
    public void correctWritaByMember(Writa writa) {
        System.out.println("Modification of a WRITA line not confirmed by a member (nbHours)");

        for (LineWrita line : writa.getListLineWrita()) {
            if (line.getLwStatus().equals(LwStatus.NOTCONFIRMED.toString())) {
                line.modifyLineWrita(12, line.getProject(), line.getIntervention(), line.getActivity());
            }
        }

        System.out.println("Deleting an erroneous line in the WRITA (last line)\n");
        writa.removeLineWrita(writa.getListLineWrita().get(writa.getListLineWrita().size() - 1));
    }

    /**
     * Method to check writa by project manager after correction
     *
     * @param writa a writa
     * @param projectManager a project manager
     */
    public void checkWritaAgainByProjectManager(Writa writa, ProjectManager projectManager) {
        for (LineWrita line : writa.getListLineWrita()) {
            if (line.getProject().getProjectManager().equals(projectManager)) {
                line.changeStatus(LwStatus.CONFIRMED);
            }
        }
        System.out.println(writa + "\n");
    }

    /**
     * Method to create a summary for a project
     *
     * @param comment
     * @return
     */
    public Summary createSummary(String comment) {
        System.out.println("Creation of a summary by a project manager (with comment)\n");
        Project projectSummary = this.getListProject().get(3);
        Summary summary = new Summary(projectSummary);

        for (LineWrita line : this.getListLineWrita()) {
            if (line.getProject().equals(projectSummary)) {
                summary.updateSummary(line);
            }
        }
        summary.writeSummary(comment);

        return summary;
    }

    /**
     * Method to consult a summary
     *
     * @param summary a summary
     */
    public void consultSummary(Summary summary) {
        System.out.println("Consulting a summary by a project manager or DED :\n");
        System.out.println(summary.consultSummary());
    }

    /**
     * Method to change a summary by a project manager
     *
     * @param summary
     */
    public void modifySummary(Summary summary) {
        System.out.println("\n");
        System.out.println("Modification of a summary by a project manager (last line)");
        summary.updateSummary(new LineWrita(12, summary.getProject(), this.getListProjectMember().get(2), this.getListIntervention().get(5), this.getListIntervention().get(5).getActivity()));
        System.out.println(summary.consultSummary());

    }

    /**
     * Method to send a summary (validation)
     *
     * @param summary
     */
    public void sendSummary(Summary summary) {
        System.out.println("Sending a summary by a project manager");
        summary.changeStatus();
        System.out.println(summary.getSuStatus());
        System.out.println("\n");
    }

    public void indicators() {
        System.out.println("Indicators : \n");
        Project project1 = this.getListProject().get(9);
        System.out.println("Project Manager with less disparity : " + this.getProjectManagerWithLessDisparity() + "\n");
        ProjectManager projectManager = this.getListProjectManager().get(3);

        System.out.println("Test getNbHoursCarriedOutByMembers & getNbHoursExpectedByMembers : ");
        System.out.println(projectManager);
        System.out.println("\t Number of carried out hours by team : " + projectManager.getNbHoursCarriedOutByMembers(this.getListProject()));
        System.out.println("\t Number of expected hours by team : " + projectManager.getNbHoursExpectedByMembers(this.getListProject()) + "\n");
        System.out.println("Activie(s) for " + project1);
        for (Map.Entry<Activity, Integer> entry : project1.getDisparityForEachActivity().entrySet()) {
            System.out.println("\t" + entry.getKey() + ", Disparity : " + entry.getValue());
        }
        System.out.println("\n");
        System.out.println("Projects with the most required activity (max hours expected) :\n");
        for (Project project : this.getListProject()) {
            int maxTime = 0;
            Activity activityMostRequired = new Activity("", "");
            for (Map.Entry<Activity, Integer> entry : project.getDisparityForEachActivity().entrySet()) {
                if (entry.getValue() > maxTime) {
                    maxTime = entry.getValue();
                    activityMostRequired = entry.getKey();
                }
            }
            System.out.println("Project : " + project + "\n\t Activity most required : " + activityMostRequired + "  Hours expected : " + maxTime);
        }
        System.out.println("\n");
        
        System.out.print("Average number of activities for projects : ");
        int sumActivities = 0;
        for (Project project : this.getListProject()) {
            sumActivities += project.getDisparityForEachActivity().size();
        }
        System.out.println(sumActivities/this.listProject.size());

    }

    /**
     * Method which get the project manager with the less disparity between
     * hours carried out and scheduled
     *
     * @return ProjectManager a project manager
     */
    public ProjectManager getProjectManagerWithLessDisparity() {
        ProjectManager bestProjectManager = this.getListPM().get(0);
        int disparity = 0;
        int disparityBest = 0;
        for (ProjectManager projectManager : this.getListPM()) {
            disparity = projectManager.getNbHoursCarriedOutByMembers(listProject) - projectManager.getNbHoursExpectedByMembers(listProject);
            disparityBest = bestProjectManager.getNbHoursCarriedOutByMembers(listProject) - bestProjectManager.getNbHoursExpectedByMembers(listProject);
            if (disparity < disparityBest) {
                bestProjectManager = projectManager;
            }
        }

        return bestProjectManager;
    }

    public List<Project> getListProject() {
        return this.listProject;
    }

    public List<ProjectManager> getListPM() {
        return this.listProjectManager;
    }

    public List<ProjectManager> getListProjectManager() {
        return this.listProjectManager;
    }

    public List<ProjectMember> getListProjectMember() {
        return this.listProjectMember;
    }

    public List<Skill> getListSkill() {
        return this.listSkill;
    }

    public List<LineWrita> getListLineWrita() {
        return this.listLineWrita;
    }

    public List<Activity> getListActivity() {
        return this.listActivity;
    }

    public List<Intervention> getListIntervention() {
        return this.listIntervention;
    }

}
