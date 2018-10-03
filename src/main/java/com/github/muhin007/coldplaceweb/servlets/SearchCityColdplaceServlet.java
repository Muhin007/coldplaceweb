package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.City;
import com.github.muhin007.coldplaceweb.data.ReadDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCityColdplaceServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("name", "");
                    resp.getWriter().println(PageGenerator.instance().getPage("searchCity.html", pageVariables));
                }
        );

    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    String message = request.getParameter("name");

                    List<City> cities = ReadDB.readCityFromDB();
                    City foundedCity = null;
                    for (City city : cities) {
                        if (message.equalsIgnoreCase(city.getName())) {
                            foundedCity = city;

                            break;
                        }
                    }

                    if (foundedCity != null) {

                        pageVariables.put("cityName", message);
                        pageVariables.put("minTemp", foundedCity.calculateRandomTemperature());
                        resp.getWriter().println(PageGenerator.instance().getPage("answerSearchCity.html",
                                pageVariables));

                    } else {
                        pageVariables.put("cityNotFound", "Города нет в списке");
                        resp.getWriter().println(PageGenerator.instance().getPage("searchCity.html", pageVariables));
                    }
                }
        );
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

}


