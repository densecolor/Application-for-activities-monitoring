/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ut1.ipm.uml.project;

import fr.ut1.ipm.uml.project.summary.Summary;
import fr.ut1.ipm.uml.project.data.ProjectManager;
import fr.ut1.ipm.uml.project.report.Writa;
import fr.ut1.ipm.uml.dao.Dao;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author 21503816
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {

        Facade facade = new Facade(new Dao());
        Writa writaTest = facade.createWrita();
        ProjectManager projectManager2 = facade.checkWritaByProjectManager(writaTest);
        facade.correctWritaByMember(writaTest);
        facade.checkWritaAgainByProjectManager(writaTest, projectManager2);
        
        Summary summary = facade.createSummary("Summary ok");
        facade.consultSummary(summary);
        facade.modifySummary(summary);
        System.out.println("\n");
        facade.sendSummary(summary);
        
        facade.indicators();


    }

}
