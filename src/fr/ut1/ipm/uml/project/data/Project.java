/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.data;

import fr.ut1.ipm.uml.project.report.LineWrita;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * A project for a business unit
 *
 * @author 21503816
 */
public class Project {

    private static final AtomicInteger count = new AtomicInteger(99);
    private final String projCode;
    private final String projName;
    private final String projObj;
    private final String projDesc;
    private final Date projStarting;
    private final Double projLong;
    private final ProjectManager projectManager;
    private final HashMap<Activity, Integer> mapActivity;
    private final List<LineWrita> listLW;
    private final BusinessUnit businessUnit;

    public Project(String projName, String projObjet, String projDesc, Date projStarting, Double projLong, ProjectManager projectManager, String unitCode, String unitName) {
        this.projStarting = projStarting;
        this.projCode = "P" + this.getYear() + count.incrementAndGet();
        this.projName = projName;
        this.projObj = projObjet;
        this.projDesc = projDesc;
        this.projLong = projLong;
        this.projectManager = projectManager;
        this.businessUnit = new BusinessUnit(unitCode, unitName);
        this.mapActivity = new HashMap();
        this.listLW = new ArrayList();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.projCode);
        hash = 47 * hash + Objects.hashCode(this.projName);
        hash = 47 * hash + Objects.hashCode(this.projObj);
        hash = 47 * hash + Objects.hashCode(this.projDesc);
        hash = 47 * hash + Objects.hashCode(this.projStarting);
        hash = 47 * hash + Objects.hashCode(this.projLong);
        hash = 47 * hash + Objects.hashCode(this.projectManager);
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
        final Project other = (Project) obj;
        if (!Objects.equals(this.projCode, other.projCode)) {
            return false;
        }
        if (!Objects.equals(this.projName, other.projName)) {
            return false;
        }
        if (!Objects.equals(this.projObj, other.projObj)) {
            return false;
        }
        if (!Objects.equals(this.projStarting, other.projStarting)) {
            return false;
        }
        if (!Objects.equals(this.projLong, other.projLong)) {
            return false;
        }
        return Objects.equals(this.projectManager, other.projectManager);
    }

    /**
     * Method to get the disparity between hours carried out and hours scheduled
     * for all activities of the project
     *
     * @return disparity for each activity of the project
     */
    public HashMap<Activity, Integer> getDisparityForEachActivity() {
        HashMap<Activity, Integer> mapDisparity = new HashMap();
        int disparity = 0;
        for (Map.Entry<Activity, Integer> entry : this.getMapActivity().entrySet()) {
            disparity = this.getCarriedOutHoursForActivity(entry.getKey()) - this.getExpectedHoursForActivity(entry.getKey());
            mapDisparity.put(entry.getKey(), disparity);
        }
        return mapDisparity;
    }

    /**
     * Method to get hours expected for an activity of a project
     *
     * @param activity an activity
     * @return Integer hours expected
     */
    public Integer getExpectedHoursForActivity(Activity activity) {
        return this.getMapActivity().get(activity);
    }

    /**
     * Method to get hours carried out for an activity of a project
     *
     * @param activity an activity
     * @return Integer
     */
    public Integer getCarriedOutHoursForActivity(Activity activity) {
        int nbHoursCarriedOutActivity = 0;
        for (LineWrita line : this.getListLW()) {
            if (line.getActivity().equals(activity)) {
                nbHoursCarriedOutActivity += line.getNbHoursCarriedOut();
            }
        }
        return nbHoursCarriedOutActivity;
    }

    /**
     * Method to get hours carried out for a project
     *
     * @return Integer hours carried out
     */
    public Integer getNbHoursCarriedOutForProject() {
        int totalNbHoursCarriedOut = 0;
        for (LineWrita line : this.getListLW()) {
            if (line.getProject().getProjCode().equals(this.getProjCode())) {
                totalNbHoursCarriedOut += line.getNbHoursCarriedOut();
            }
        }
        return totalNbHoursCarriedOut;
    }

    /**
     * Method to get hours expected for a project
     *
     * @return Integer hours expected
     */
    public Integer getExpectedHoursForProject() {
        int totalNbHoursProject = 0;
        for (Map.Entry<Activity, Integer> entry : this.getMapActivity().entrySet()) {
            totalNbHoursProject += entry.getValue();
        }
        return totalNbHoursProject;
    }

    /**
     * Method to add an activity with these hours expected to a project
     *
     * @param activity a activity
     * @param nbHoursExpected a number expected hours
     */
    public void addToMapActivity(Activity activity, Integer nbHoursExpected) {
        this.getMapActivity().put(activity, nbHoursExpected);
    }

    /**
     * Method to get the current year (2 characters)
     *
     * @return a year 'YY'
     */
    private String getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        return sdf.format(this.getProjStarting());
    }

    /**
     * Method to add writa line to a project
     *
     * @param line a line of writa
     */
    public void addToListLw(LineWrita line) {
        this.listLW.add(line);
    }

    public String getProjCode() {
        return this.projCode;
    }

    public String getProjName() {
        return this.projName;
    }

    public String getProjObjet() {
        return this.projObj;
    }

    public Date getProjStarting() {
        return this.projStarting;
    }

    public Double getProjLong() {
        return this.projLong;
    }

    public String getProjObj() {
        return this.projObj;
    }

    public List<LineWrita> getListLW() {
        return this.listLW;
    }

    public BusinessUnit getBusinessUnit() {
        return this.businessUnit;
    }

    public ProjectManager getProjectManager() {
        return this.projectManager;
    }

    public HashMap<Activity, Integer> getMapActivity() {
        return this.mapActivity;
    }

    public String getProjDesc() {
        return this.projDesc;
    }

    @Override
    public String toString() {
        return "Project : " + "Code=" + projCode + ", Name=" + projName + ", Objet=" + projObj + ", Description=" + projDesc + ", Starting=" + projStarting + ", Long=" + projLong;
    }

}
