package com.example.taskmanager.controller;

import com.example.taskmanager.dao.impl.ProjectDaoImpl;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.service.ProjectService;
import com.example.taskmanager.model.Status;
import com.example.taskmanager.service.impl.ProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ProjectControllerServlet extends HttpServlet {

	private ProjectService projectService;

	@Override
	public void init() throws ServletException {
		projectService = new ProjectServiceImpl(new ProjectDaoImpl());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null || action.isEmpty()) {
			action = "list";
		}

		switch (action) {
		case "new":
			showNewForm(request, response);
			break;
		case "edit":
			showEditForm(request, response);
			break;
		case "delete":
			deleteProject(request, response);
			break;
		case "view":
			viewProject(request, response);
			break;
		default:
			listProjects(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("create".equals(action)) {
			createProject(request, response);
		} else if ("update".equals(action)) {
			updateProject(request, response);
		}
	}

	private void listProjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = getIntParameter(request, "page", 1); // Default to page 1
		int pageSize = getIntParameter(request, "pageSize", 10); // Default to pageSize 10
		List<Project> projects = projectService.getAllProjects(page, pageSize);
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("/project-list.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/project-form.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = getIntParameter(request, "id");
		Project project = projectService.findProjectById(projectId);
		request.setAttribute("project", project);
		request.getRequestDispatcher("/project-form.jsp").forward(request, response);
	}

	private void createProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Project project = getProjectFromRequest(request);
		projectService.createProject(project);
		response.sendRedirect("projects?action=list");
	}

	private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = getIntParameter(request, "id");
		Project project = getProjectFromRequest(request);
		project.setId(id);
		projectService.updateProject(project);
		response.sendRedirect("projects?action=list");
	}

	private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int projectId = getIntParameter(request, "id");
		projectService.deleteProject(projectId);
		response.sendRedirect("projects?action=list");
	}

	private void viewProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int projectId = getIntParameter(request, "id");
		Project project = projectService.findProjectById(projectId);
		request.setAttribute("project", project);
		request.getRequestDispatcher("/project-view.jsp").forward(request, response);
	}

	// Helper method to retrieve a project from request parameters
	private Project getProjectFromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
		Status status = Status.valueOf(request.getParameter("status"));
		return new Project(name, description, startDate, endDate, status);
	}

	// Helper method to parse integer parameters safely
	private int getIntParameter(HttpServletRequest request, String name) {
		return Integer.parseInt(request.getParameter(name));
	}

	// Helper method with a default value if the parameter doesn't exist or is
	// invalid
	private int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
		try {
			return Integer.parseInt(request.getParameter(name));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
