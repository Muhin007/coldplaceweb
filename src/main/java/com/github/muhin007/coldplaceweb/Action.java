package com.github.muhin007.coldplaceweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Action {
    void action(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException;

}