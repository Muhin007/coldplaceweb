package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.ReadDB;
import com.github.muhin007.coldplaceweb.data.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = new HashMap<>();
                    resp.getWriter().println(PageGenerator.instance().
                            getPage("signIn.html", pageVariables));
                }
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {

                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    String login = req.getParameter("login");
                    String pass = req.getParameter("pass");

                    if (login == null || pass == null) {

                        pageVariables.put("message", "Вы ввели не все данные в форму. Попробуйте еще раз.");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("repeatedSignIn.html", pageVariables));
                        return;
                    } else {

                        List<UserProfile> users = ReadDB.readUserFromDB();

                        UserProfile foundedUser = null;
                        UserProfile foundedPass = null;

                        for (UserProfile user : users) {
                            if (login.equals(user.getLogin())) {
                                foundedUser = user;
                            }
                        }
                        for (UserProfile password : users) {
                            if (pass.equals(password.getPass())) {
                                foundedPass = password;
                            }
                        }
                        if (foundedUser != null && foundedPass != null) {

                            pageVariables.put("message", "Пользователь " + login + " Добро пожаловать в систему.");
                            resp.getWriter().println(PageGenerator.instance().
                                    getPage("registrationAnswer.html", pageVariables));
                        }else {
                            pageVariables.put("message", "Неверный логин или пароль");
                            resp.getWriter().println(PageGenerator.instance().
                                    getPage("repeatedSignIn.html", pageVariables));
                        }
                    }

                }
        );
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(null, request.getParameterMap().toString());
        return pageVariables;
    }
}
