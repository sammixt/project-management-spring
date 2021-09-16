package com.sammixt.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sammixt.pma.dao.EmployeeRepository;
import com.sammixt.pma.dao.ProjectRepository;
import com.sammixt.pma.dto.ChartData;
import com.sammixt.pma.dto.EmployeeProject;
import com.sammixt.pma.entites.Project;

@Controller
public class HomeController {

	//@Value("${version}")
	private String ver = "2.2";
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		List<Project> projects = proRepo.findAll();
		
		model.addAttribute("versionNumber", ver);
		
		model.addAttribute("projectsList",projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		List<EmployeeProject> employeesListProjectsCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt",employeesListProjectsCnt);
		return "main/home";
	}
}
