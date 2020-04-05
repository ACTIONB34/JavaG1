/**
 * 
 */
package com.aws.JavaG1.utilities;
import com.aws.JavaG1.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
/**
 * @author bianca.gilay
 *
 */
public class gui {
	//get all movies
	//get all timeslots
	//get all cinemas
	//populate cinemas w/ timeslots and movies
	//get all reservations by name
	private JFrame frame;
	private JPanel mainPanel;
	private JTextArea cine1;
	private JTextArea cine2;
	private JTextArea cine3;
	private JTextArea cine4;
	private Font head_font;	//Font font = new Font("Segoe Script", Font.BOLD, 20);
	
	public gui(ArrayList<Cinema> show) { //Cinema show1, Cinema show2, Cinema show3, Cinema show4
		frame = new JFrame("Java Group 1 - Online Movie Reservation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		head_font = new Font("Gadugi", Font.BOLD, 20);
		
		//textArea.setLineWrap(true);
		//textArea.setText("The quick brown fox jumps over the lazy dog.");
		
		cine1 = new JTextArea(40,17);
		cine1.setFont(head_font);
		cine1.setEditable(false);
		cine1.setLineWrap(true);
		cine1.setText(" CINEMA 1\n\n\n" + getCinemaDetails(show.get(0)));
		
		cine2 = new JTextArea(40,17);
		cine2.setFont(head_font);
		cine2.setEditable(false);
		cine2.setLineWrap(true);
		cine2.setText(" CINEMA 2\n\n\n" + getCinemaDetails(show.get(1)));
		
		cine3 = new JTextArea(40,17);
		cine3.setFont(head_font);
		cine3.setEditable(false);
		cine3.setLineWrap(true);
		cine3.setText(" CINEMA 3\n\n\n" + getCinemaDetails(show.get(2)));
		
		cine4 = new JTextArea(40,17);
		cine4.setFont(head_font);
		cine4.setEditable(false);
		cine4.setLineWrap(true);
		cine4.setText(" CINEMA 4\n\n\n" + getCinemaDetails(show.get(3)));
				
		mainPanel = new JPanel(new FlowLayout());
		mainPanel.setPreferredSize(new Dimension(1250,515));
		mainPanel.setBackground(Color.black);
		mainPanel.add(cine1);
		mainPanel.add(cine2);
		mainPanel.add(cine3);
		mainPanel.add(cine4);
		mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		frame.add(mainPanel);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

    public String getCinemaDetails(Cinema cinema){
		//title
    	//director
    	//genre
    	//rating
    	//screening time: show 1, 2, 3
    	String info;
    	
    	info = "  Title: " + cinema.getMovie().getMovieName() + "\n";
    	info += "  Directed by: " + cinema.getMovie().getMovieDirector() + "\n";
    	info += "  Genre: " + cinema.getMovie().getMovieGenre() + "\n";
    	info += "  Rating: " + cinema.getMovie().getMovieRating() +"\n";
    	info += "\n  Screening Time\n\n";
    	
//    	ArrayList<Timeslot> ts = new ArrayList<Timeslot>();
//    	ts = cinema.getTimeslots();
//    	for(int i =0; i<3; i++) {
//    		info += "Show " + i+1 + "- " + ts.get(i) + "\n";
//    	}
    	int i = 1;
    	for (Timeslot timeslot : cinema.getTimeslots()) {
    		info += "  Show " + i++ + ": " + timeslot.getTimeStart() + "\n";
    		}
    	
    	return info;
    	}
	
}
