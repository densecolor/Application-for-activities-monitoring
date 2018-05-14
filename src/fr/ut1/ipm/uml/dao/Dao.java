/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.dao;

import fr.ut1.ipm.uml.project.data.Activity;
import fr.ut1.ipm.uml.project.data.Intervention;
import fr.ut1.ipm.uml.project.data.Project;
import fr.ut1.ipm.uml.project.data.ProjectManager;
import fr.ut1.ipm.uml.project.data.ProjectMember;
import fr.ut1.ipm.uml.project.data.Schedule;
import fr.ut1.ipm.uml.project.data.Skill;
import fr.ut1.ipm.uml.project.report.LineWrita;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to data access
 *
 * @author 21503816
 */
public class Dao {

    private static String FICHIER;
    private final List<Project> listProject;
    private final List<ProjectManager> listProjectManager;
    private final List<ProjectMember> listProjectMember;
    private final List<Skill> listSkill;
    private final List<LineWrita> listLineWrita;
    private final List<Activity> listActivity;
    private final List<Intervention> listIntervention;
    private final List<Schedule> listHoursScheduled;

    public Dao() throws IOException, ParseException {
        this.listProjectManager = new ArrayList();
        this.listProjectMember = new ArrayList();
        this.listSkill = new ArrayList();
        this.listActivity = new ArrayList();
        this.listIntervention = new ArrayList();
        this.listProject = new ArrayList();
        this.listHoursScheduled = new ArrayList();
        this.listLineWrita = new ArrayList();
        this.readActivities();
        this.readInterventions();
        this.readProjectManagers();
        this.readProjectMembers();
        this.readSkills();
        this.readProjects();
        this.readHoursScheduled();
        this.readLinesWrita();
    }

    /*----------*/
 /* Methods */
 /*----------*/
    /**
     * Reading of data files.
     *
     * @throws java.io.IOException
     */
    private void readSkills() throws IOException {
        FICHIER = "skill";
        Skill s;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    String skCode = csvLine[0];
                    String skName = csvLine[1];
                    s = new Skill(skCode, skName);
                    listSkill.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readProjectManagers() throws IOException {
        FICHIER = "projectmanager";
        ProjectManager s;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new ProjectManager(csvLine[0], csvLine[1], csvLine[2], csvLine[3]);
                    this.listProjectManager.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readProjectMembers() throws IOException {
        FICHIER = "projectmember";
        ProjectMember s;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new ProjectMember(csvLine[0], csvLine[1], csvLine[2], csvLine[3]);
                    this.listProjectMember.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readProjects() throws IOException, ParseException {
        FICHIER = "project";
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        Project s;
        int projectManagerCounter = 0;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new Project(csvLine[0], csvLine[1], csvLine[2], df.parse(csvLine[3]), Double.parseDouble(csvLine[4]), this.listProjectManager.get(projectManagerCounter), csvLine[5], csvLine[6]);
                    projectManagerCounter++;

                    if (projectManagerCounter >= this.listProjectManager.size()) {
                        projectManagerCounter = 0;
                    }
                    this.listProject.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readActivities() throws IOException, ParseException {
        FICHIER = "activity";
        Activity s;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new Activity(csvLine[0], csvLine[1]);
                    this.listActivity.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readInterventions() throws IOException, ParseException {
        FICHIER = "intervention";
        Intervention s;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new Intervention(csvLine[0], csvLine[1], csvLine[2]);
                    this.listIntervention.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readLinesWrita() throws IOException, ParseException {
        FICHIER = "linewrita";
        LineWrita s;
        int projectCounter = 0;
        int interventionCounter = 0;
        int projectMemberCounter = 0;
        int activityCounter = 0;

        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new LineWrita(Integer.valueOf(csvLine[0]), this.listProject.get(projectCounter), this.listProjectMember.get(projectMemberCounter), this.listIntervention.get(interventionCounter), this.listActivity.get(activityCounter));

                    projectCounter++;
                    projectMemberCounter++;
                    interventionCounter++;
                    activityCounter++;

                    if (projectCounter >= this.listProject.size()) {
                        projectCounter = 0;
                    }

                    if (projectMemberCounter >= this.listProjectMember.size()) {
                        projectMemberCounter = 0;
                    }

                    if (interventionCounter >= this.listIntervention.size()) {
                        interventionCounter = 0;
                    }

                    if (activityCounter >= this.listActivity.size()) {
                        activityCounter = 0;
                    }

                    this.listLineWrita.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    private void readHoursScheduled() throws IOException, ParseException {
        FICHIER = "schedule";
        Schedule s;
        int projectCounter = 0;
        int activityCounter = 0;
        int projectMemberCounter = 0;
        try {
            try (BufferedReader fichier_source = new BufferedReader(new FileReader(FICHIER + ".csv"))) {
                String sk;
                while ((sk = fichier_source.readLine()) != null) {
                    String[] csvLine = sk.split(";");
                    s = new Schedule(Integer.valueOf(csvLine[0]), this.listActivity.get(activityCounter), this.listProject.get(projectCounter), this.listProjectMember.get(projectMemberCounter));

                    if (projectCounter >= this.listProject.size()) {
                        projectCounter = 0;
                    }

                    if (activityCounter >= this.listActivity.size()) {
                        activityCounter = 0;
                    }
                    if (projectMemberCounter >= this.listProjectMember.size()) {
                        projectMemberCounter = 0;
                    }
                    this.listHoursScheduled.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }
    }

    public List<Project> getListProject() {
        return this.listProject;
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

    public List<Schedule> getListHoursScheduled() {
        return listHoursScheduled;
    }

}
