/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project.summary;

import fr.ut1.ipm.uml.project.data.Project;
import fr.ut1.ipm.uml.project.enumeration.Status;
import fr.ut1.ipm.uml.project.report.LineWrita;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * Activities, interventions, hours carried out for a project, a week, a year
 *
 * @author 21503816
 */
public class Summary {

    private final String SuNumWeek;
    private final String SuYear;
    private String textArea;
    private Enum SuStatus;
    private final Project project;
    private final List<LineWrita> listLW;

    public Summary(Project project) {
        this.SuYear = this.getCurrentYear();
        this.SuNumWeek = this.getCurrentWeek();
        this.project = project;
        this.textArea = "";
        this.SuStatus = Status.SAVED;
        this.listLW = new ArrayList();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.SuNumWeek);
        hash = 53 * hash + Objects.hashCode(this.SuYear);
        hash = 53 * hash + Objects.hashCode(this.textArea);
        hash = 53 * hash + Objects.hashCode(this.SuStatus);
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
        final Summary other = (Summary) obj;
        if (!Objects.equals(this.SuNumWeek, other.SuNumWeek)) {
            return false;
        }
        if (!Objects.equals(this.SuYear, other.SuYear)) {
            return false;
        }
        if (!Objects.equals(this.textArea, other.textArea)) {
            return false;
        }
        return this.SuStatus == other.SuStatus;
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
     * Method to transform a collection of writa line into a summary (sum of
     * hours carried out for an activity and details about interventions)
     *
     * @return a summary
     */
    public String transformWritaLineToSummary() {
        String summary = "";
        String lines;
        List<LineWrita> treat = new ArrayList();

        //2 loop to compare lines of the list
        for (LineWrita line : this.getListLw()) {

            //we store the referency intervention line 
            lines = "";
            int nbHoursTotal = line.getNbHoursCarriedOut();
            lines += "\tActivity :    Project :      Description of intervention :        Weekly time (hours) :\n";
            lines += "\t" + line.getActivity().getActCode() + "        " + line.getProject().getProjCode() + "         "
                    + line.getIntervention().getInterDesc() + "                                 " + line.getNbHoursCarriedOut();
            lines += "\n";

            //we compare with the others lines of the list
            for (LineWrita lineCompared : this.getListLw()) {

                //if it's the same activity, we add the intervetion into the intervention lines stored
                if (line.getActivity().equals(lineCompared.getActivity()) && !treat.contains(lineCompared) && !line.equals(lineCompared)) {
                    nbHoursTotal += lineCompared.getNbHoursCarriedOut();
                    treat.add(lineCompared);
                    lines += "\t" + lineCompared.getActivity().getActCode() + "        " + lineCompared.getProject().getProjCode() + "         "
                            + lineCompared.getIntervention().getInterDesc() + "                                 " + lineCompared.getNbHoursCarriedOut();
                    lines += "\n";
                }
            }

            //we get the activity for all interventions
            summary += line.getActivity().getActName() + "         " + line.getActivity().getActDesc()
                    + "                      " + line.getProject().getProjCode() + "                " + nbHoursTotal;
            summary += "\n";

            //we print the activity then the corresponding interventions
            summary += lines;
        }
        summary += "Project manager review :                                                            ";

        return summary;
    }

    /**
     * Method to change the status of a summary (sent the summary to the project
     * manager)
     */
    public void changeStatus() {
        this.SuStatus = Status.SENT;
    }

    /**
     * Method to write a summary
     *
     * @param comment a comment
     * @return a summary
     */
    public String writeSummary(String comment) {
        String summary = this.transformWritaLineToSummary();
        this.textArea = comment;
        summary += this.getTextArea();
        return summary;
    }

    /**
     * Method to consul a summary (lines of WRITA + comment)
     *
     * @return a summary
     */
    public String consultSummary() {
        String writa = "*********************************************WEEKLY SUMMARIES OF IT ACTIVITIES**************************************************\n";
        writa += "Name of " + this.getProject().getProjectManager() + "                   " + "Year : " + this.getCurrentYear() + "\n";
        writa += "Staff number : " + this.getProject().getProjectManager().getStaffCode() + "                                                                              " + "Week n° : " + this.getCurrentWeek() + "\n \n";
        writa += "Activity :                              Description of activiry :      Project code :       Weekly time (hours) :\n";
        writa += this.writeSummary(this.getTextArea()) + "\n";
        writa += "******************************************************************************************************************************";
        return writa;
    }

    /**
     * Method to add a line of WRITA to a summary (validation of the WRITA to
     * another project member)
     *
     * @param lineWrita a line of WRITA
     */
    public void updateSummary(LineWrita lineWrita) {
        this.getListLw().add(lineWrita);
    }

    /**
     * Method to get the current year
     *
     * @return a year 'YYYY'
     */
    private String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date());
    }

    public String getSuNumWeek() {
        return this.SuNumWeek;
    }

    public String getSuYear() {
        return this.SuYear;
    }

    public Project getProject() {
        return this.project;
    }

    public List<LineWrita> getListLw() {
        return this.listLW;
    }

    public String getTextArea() {
        return textArea;
    }

    public String getSuStatus() {
        return this.SuStatus.toString();
    }

}
