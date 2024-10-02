package com.example.taskmanager.controller;

import com.example.taskmanager.model.Member;
import com.example.taskmanager.service.MemberService;
import com.example.taskmanager.service.impl.MemberServiceImpl;
import com.example.taskmanager.dao.impl.MemberDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberControllerServlet extends HttpServlet {

    private MemberService memberService;

    @Override
    public void init() throws ServletException {
        memberService = new MemberServiceImpl(new MemberDaoImpl());
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
                deleteMember(request, response);
                break;
            default:
                listMembers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createMember(request, response);
        } else if ("update".equals(action)) {
            updateMember(request, response);
        }
    }

    private void listMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = getIntParameter(request, "teamId", 0);  // Default to 0 (no team)
        int page = getIntParameter(request, "page", 1);  // Default to page 1
        int pageSize = getIntParameter(request, "pageSize", 10);  // Default to pageSize 10
        List<Member> members = memberService.getAllMembersByTeamId(teamId, page, pageSize);
        request.setAttribute("members", members);
        request.getRequestDispatcher("/member-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/member-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = getIntParameter(request, "id");
        Member member = memberService.findMemberById(memberId);
        request.setAttribute("member", member);
        request.getRequestDispatcher("/member-form.jsp").forward(request, response);
    }

    private void createMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Member member = getMemberFromRequest(request);
        memberService.createMember(member);
        response.sendRedirect("members?action=list");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getIntParameter(request, "id");
        Member member = getMemberFromRequest(request);
        member.setId(id);
        memberService.updateMember(member);
        response.sendRedirect("members?action=list");
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int memberId = getIntParameter(request, "id");
        memberService.deleteMember(memberId);
        response.sendRedirect("members?action=list");
    }

    // Helper method to retrieve a member from request parameters
    private Member getMemberFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        return new Member(firstName, lastName, email, role, teamId);
    }

    // Helper method to parse integer parameters safely
    private int getIntParameter(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    // Helper method with a default value if the parameter doesn't exist or is invalid
    private int getIntParameter(HttpServletRequest request, String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
