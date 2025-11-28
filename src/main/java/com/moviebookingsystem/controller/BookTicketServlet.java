package com.moviebookingsystem.controller;

import com.moviebookingsystem.model.Movie;
import com.moviebookingsystem.model.Seat;
import com.moviebookingsystem.dao.MovieDAO;
import com.moviebookingsystem.dao.SeatDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/bookTicket")
public class BookTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = 0;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user-id".equals(c.getName())) {
                    userId = Integer.parseInt(c.getValue());
                    break;
                }
            }
        }
        if (userId == 0) {
            resp.sendRedirect("index.jsp");
            return;
        }

        int movieId = Integer.parseInt(req.getParameter("movie-id"));

        Movie movie = MovieDAO.getMovieById(movieId);
        ArrayList<Seat> availableSeats = SeatDAO.getAvailableSeats(movieId);

        req.setAttribute("movie", movie);
        req.setAttribute("availableSeats", availableSeats);

        req.getRequestDispatcher("view/seatSelection.jsp").forward(req, resp);
    }
}
