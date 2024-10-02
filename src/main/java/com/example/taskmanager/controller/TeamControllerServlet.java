package com.example.taskmanager.controller;

import com.example.taskmanager.model.Team;
import com.example.taskmanager.service.TeamService;
import com.example.taskmanager.service.impl.TeamServiceImpl;
import com.example.taskmanager.dao.impl.TeamDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamControllerServlet extends HttpServlet {

    private TeamService teamService;

    @Override
    public void init() throws ServletException {
        teamService = new TeamServiceImpl(new TeamDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                deleteTeam(request, response);
                break;
            case "view":
                viewTeam(request, response);
                break;
            default:
                listTeams(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTeam(request, response);
        } else if ("update".equals(action)) {
            updateTeam(request, response);
        }
    }

    private void listTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = getIntParameter(request, "page", 1);  
        int pageSize = getIntParameter(request, "pageSize", 10);  
        List<Team> teams = teamService.getAllTeams(page, pageSize);
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/team-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/team-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = getIntParameter(request, "id");
        Team team = teamService.findTeamById(teamId);
        request.setAttribute("team", team);
        request.getRequestDispatcher("/team-form.jsp").forward(request, response);
    }

    private void createTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Team team = getTeamFromRequest(request);
        teamService.createTeam(team);
        response.sendRedirect("teams?action=list");
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIntParameter(request, "id");
        Team team = getTeamFromRequest(request);
        team.setId(id);
        teamService.updateTeam(team);
        response.sendRedirect("teams?action=list");
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int teamId = getIntParameter(request, "id");
        teamService.deleteTeam(teamId);
        response.sendRedirect("teams?action=list");
    }

    private void viewTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = getIntParameter(request, "id");
        Team team = teamService.findTeamById(teamId);
        request.setAttribute("team", team);
        request.getRequestDispatcher("/team-view.jsp").forward(request, response);
    }

    private Team getTeamFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        return new Team(name, null);
    }

    private int getIntParameter(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    private int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
