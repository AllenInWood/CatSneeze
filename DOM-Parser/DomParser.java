package dom;
import dom.Movies;
import dom.Stars;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {

	//No generics
	public HashMap<String, Movies> myMovies;
	public HashMap<String, Stars> myStars;
	public HashMap<String, Integer> allGenres;
	public Document dom;

	public DomParser(){
		//create a list to hold the employee objects
		myMovies = new HashMap<String, Movies>();
		myStars = new HashMap<String, Stars>();
		allGenres = new HashMap<String, Integer>();
	}

	public void run() {
		
		//parse the xml file and get the dom object
		parseXmlFile("mains243.xml");
		
		//get each employee element and create a Employee object
		parseDocumentMain();

		parseXmlFile("actors63.xml");
		parseDocumentActor();

		parseXmlFile("casts124.xml");
		parseDocumentCat();
		
	}
	
	
	private void parseXmlFile(String fileName){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(fileName);
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	private void parseDocumentMain(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <employee> elements
		NodeList nl = docEle.getElementsByTagName("film");
		if(nl != null && nl.getLength() > 0) {
			for (int i = 0 ; i < nl.getLength(); ++ i) {

				Element el = (Element)nl.item(i);
				
				String id = getTextValue(el, "fid").size() == 0 ? null : getTextValue(el, "fid").get(0);
				String director = getTextValue(el, "dirn").size() == 0 ? null : getTextValue(el, "dirn").get(0);
				int year = getIntValue(el, "year");
				String title = getTextValue(el, "t").size() == 0 ? null : getTextValue(el, "t").get(0);

				if (id != null) {
					id = id.trim();
				}
				if (director != null) {
					director = director.trim();
				}
				if (title != null) {
					title = title.trim();
				}

				List<String> genres = getTextValue(el, "cat");
				for (int k = 0; k < genres.size(); ++ k) {
					String s = genres.get(k);
					if (s != null) {
						s = s.trim();
						genres.set(k, s.toLowerCase());
					}
					if (!allGenres.containsKey(genres.get(k))) {
						allGenres.put(genres.get(k), 1);
					}
					else {
						Integer n = allGenres.get(genres.get(k));
						n ++;
						allGenres.put(genres.get(k), n);
					}
				}

				// Deal with duplicate movies in the XML files
				if (myMovies.containsKey(id)) { 
					if (director != null) {
						myMovies.get(id).setDirector(director);
					}
					if (year != -1) {
						myMovies.get(id).setYear(year);
					}
					if (title != null) {
						myMovies.get(id).setTitle(title);
					}
					if (genres.size() != 0) {
						myMovies.get(id).setGenres(genres);
					}
				}

				if (genres.size() == 0) {
					if (title != null)
						System.out.println("The movie " + title + " has no genres data.");
					else
						System.out.println("The movie with id " + id + " has no genres data.");
					Movies movie = new Movies(id, title, year, director, null);
					myMovies.put(id, movie);
				}
				else {
					Movies movie = new Movies(id, title, year, director, genres);
					myMovies.put(id, movie);
				}
				if (id == null) {
					System.out.println("The movie " + title + " has no id.");
				}
				if (title == null) {
					System.out.println("The movie with id " + id + " has no name.");
				}
				if (year == -1) {
					if (title != null)
						System.out.println("The movie " + title + " has no valid year data.");
					else
						System.out.println("The movie with id " + id + " has no valid year data.");
				}
				if (director == null) {
					if (title != null)
						System.out.println("The movie " + title + " has no director data.");
					else
						System.out.println("The movie with id " + id + " has no director data.");
				}

			}
		}
		System.out.println("Complete parsing the main file!");
	}

	private void parseDocumentActor(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <employee> elements
		NodeList nl = docEle.getElementsByTagName("actor");
		if(nl != null && nl.getLength() > 0) {
			for (int i = 0 ; i < nl.getLength(); ++ i) {

				Element el = (Element)nl.item(i);
				
				String stageName = getTextValue(el, "stagename").size() == 0 ? null : getTextValue(el, "stagename").get(0);
				String firstName = getTextValue(el, "firstname").size() == 0 ? null : getTextValue(el, "firstname").get(0);
				String lastName = getTextValue(el, "familyname").size() == 0 ? null : getTextValue(el, "familyname").get(0);
				if (firstName != null) {
					firstName = firstName.trim();
				}
				if (lastName != null) {
					lastName = lastName.trim();
				}
				String name = firstName + " " + lastName;
				int birthYear = getIntValue(el, "dob");
				
				if (myStars.containsKey(stageName)) {
					if (firstName != null && lastName != null) {
						myStars.get(stageName).setName(name);
					}
					if (birthYear != -1) {
						myStars.get(stageName).setBirthYear(birthYear);
					}
				}
				else {
					Stars star = new Stars(stageName, name, birthYear);
					myStars.put(stageName, star);
				}

				if (firstName == null) {
					System.out.println("The actor with stage name: " + stageName + " has no first name.");
				}
				if (lastName == null) {
					System.out.println("The actor with stage name: " + stageName + " has no last name.");
				}
				if (birthYear == -1) {
					System.out.println("The birth year of the actor with stage name: " + stageName + " is unknown or invalid.");
				}
			}
		}
		System.out.println("Complete parsing the casts file!");
	}

	private void parseDocumentCat(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <employee> elements
		NodeList nl = docEle.getElementsByTagName("m");
		if(nl != null && nl.getLength() > 0) {
			for (int i = 0 ; i < nl.getLength(); ++ i) {

				Element el = (Element)nl.item(i);
				
				String movieId = getTextValue(el, "f").size() == 0 ? null : getTextValue(el, "f").get(0);
				String starId = getTextValue(el, "a").size() == 0 ? null : getTextValue(el, "a").get(0);
				if (movieId != null) {
					movieId = movieId.trim();
				}
				if (myStars.containsKey(starId) && myMovies.containsKey(movieId)) {
					myStars.get(starId).setMovieId(movieId);
				}
				else {
					if (!myMovies.containsKey(movieId)) {
						System.out.println("The movie with movieId " + movieId + " is not found.");
					}
					else {
						System.out.println("The actor with stage name " + starId + " could not be found.");
					}
				}
				if (movieId == null) {
					System.out.println("The movie with movieId " + movieId + " has no id.");
				}
			}
		}
		System.out.println("Complete parsing the actor file!");
	}

	private void printMovies() {
		Iterator it = myMovies.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Movies m = myMovies.get(key);
			System.out.println(m.getId() + " " + m.getTitle() + " " + m.getYear() + " " + m.getDirector());
		}
	}

	private void printGenres() {
		Iterator it = allGenres.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Integer t = allGenres.get(key);
			System.out.println(String.valueOf(key) + ": " + t);
		}
	}

	private void printStars() {
		Iterator it = myStars.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Stars s = myStars.get(key);
			System.out.println(s.getId() + " " + s.getName() + " " + s.getBirthYear());
		}
	}

	private List<String> getTextValue(Element ele, String tagName) {
		List<String> textVal = new ArrayList<String>();
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); ++ i) {
				Element el = (Element)nl.item(i);
				if (el.getFirstChild() != null) {
					textVal.add(el.getFirstChild().getNodeValue());
				}
			}
		}
		return textVal;
	}

	private int getIntValue(Element ele, String tagName) {
		String pattern = "^\\d+$";
		//in production application you would catch the exception
		String content = getTextValue(ele,tagName).size() == 0 ? null : getTextValue(ele,tagName).get(0).trim();
		if (content != null && Pattern.matches(pattern, content)) {
			return Integer.parseInt(content);
		}
		return -1;
	}
}