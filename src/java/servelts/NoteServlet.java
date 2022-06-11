/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author mitch
 */
public class NoteServlet extends HttpServlet {

    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        // to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
       
        Note page = new Note(br.readLine(),br.readLine());
        
        request.setAttribute("note",page);
        br.close();
        if (request.getParameter("edit")!= null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            
            return;
        }
        
         getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            
            return;
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        Note page = new Note(title,content);
        
        request.setAttribute("note",page);
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        // to write to a file
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false))); 
        
        pw.println(title);
        pw.println(content);
        
        pw.close();
         getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            
         return;
        
    }

   

}
