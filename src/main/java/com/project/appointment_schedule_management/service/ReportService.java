// package com.project.appointment_schedule_management.service;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.util.ResourceUtils;

// import com.project.appointment_schedule_management.Interface.InterSchedule;
// import com.project.appointment_schedule_management.dao.ScheduleRepository;

// import net.sf.jasperreports.engine.*;
// import net.sf.jasperreports.engine.JRException;
// import net.sf.jasperreports.engine.JasperCompileManager;
// import net.sf.jasperreports.engine.JasperExportManager;
// import net.sf.jasperreports.engine.JasperFillManager;
// import net.sf.jasperreports.engine.JasperPrint;
// import net.sf.jasperreports.engine.JasperReport;
// import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// @Service
// public class ReportService {
    
//     @Autowired
//     private ScheduleRepository schRepository;

//     public String exportReport(int userId, LocalDate start) throws FileNotFoundException, JRException{
//         String path = "C:\\Users\\User\\Downloads\\Report";
//         List<InterSchedule> list = schRepository.dailyReport(userId, start);
//         //load file and compile it
//         File file = ResourceUtils.getFile("classpath: sch.jrxml");
//         JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//         Map<String, Object> parameters = new HashMap<>();
//         parameters.put("createdBy", "Java Techie");
//         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

//             JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Daily_Report.pdf");

//         return "report generated in path : " + path;
//     }
// }

