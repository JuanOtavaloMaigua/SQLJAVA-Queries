import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GeneralQueries{
	private static final String URL = "jdbc:derby:university;create=true";
	private Connection connection; // manages connection

	//crear, borrar y cambiar datos
	private PreparedStatement insertNewFaculty;  
	private PreparedStatement deleteFaculty;
	private PreparedStatement updateFaculty;

	private PreparedStatement verificationIDCourses;
	private PreparedStatement verificationNewCourses; 

	private PreparedStatement insertNewCourses;  
	private PreparedStatement deleteCourses;  
	private PreparedStatement updateCourses;
	private PreparedStatement verificationIDFaculty;
	//private PreparedStatement deleteEveryCourse;

	private PreparedStatement showTableFaculty;
	private PreparedStatement showTableCourse;

	private PreparedStatement completeTeacherInfo;
	private PreparedStatement commonWord;

	private PreparedStatement verificationDataRep;

	String palabra = "";

	public GeneralQueries(){

		try {

			connection = 
            DriverManager.getConnection(URL);

            //showTableFaculty
            showTableFaculty = connection.prepareStatement(
            	"SELECT * FROM Faculty"
            	);

            //showTableCourse
			showTableCourse = connection.prepareStatement(
				"SELECT * FROM Course"
				);


		// create insert that adds a new entry into the database  insertNewFaculty
         insertNewFaculty = connection.prepareStatement(         
            "INSERT INTO Faculty " +                           
            "(faculty_id, faculty_name, office) " +     
            "VALUES (?, ?, ?)");   
         
        //deleteFaculty
         deleteFaculty = connection.prepareStatement(
         	"DELETE FROM Faculty WHERE faculty_id = ?"
         	);

         //updateFaculty
         updateFaculty = connection.prepareStatement(
         	"UPDATE Faculty SET faculty_name = ?, office = ? WHERE faculty_id = ?"
         	);

         //verificationNewFaculty
         verificationIDCourses = connection.prepareStatement(
			"SELECT course_id FROM Course WHERE course_id = ?"
         	);

         //verificationIDFaculty 
         verificationIDFaculty = connection.prepareStatement(
         	"SELECT faculty_id FROM Faculty WHERE faculty_id = ?"
         	);


         //verificationNewCourses
         verificationNewCourses = connection.prepareStatement(
         	"SELECT course_id FROM Faculty INNER JOIN Course ON Faculty.faculty_id = Course.faculty_id and Faculty.faculty_id = ?"
         	);

         //connection.prepareStatement( verifica que exista un faculty_id dado
         verificationDataRep = connection.prepareStatement(
         	"SELECT faculty_id FROM Faculty WHERE faculty_id = ?"
         	);

         //insertNewCourses
         insertNewCourses = connection.prepareStatement(
         	"INSERT INTO Course " + "(course_id, course, faculty_id)" + "VALUES(?, ?, ?)"
         	);

         //deleteCourses
         deleteCourses = connection.prepareStatement(
         	"DELETE FROM Course WHERE course_id = ?"
         	);

         //updateCourses
         updateCourses = connection.prepareStatement(
         	"UPDATE Course SET course = ? WHERE course_id = ?"
         	);
		
         //Permitir la consulta completa de cursos por profesor.
         completeTeacherInfo = connection.prepareStatement(
         	"SELECT * FROM Faculty INNER JOIN Course ON Faculty.faculty_id = Course.faculty_id and Faculty.faculty_name = ?"
         	);

         //Listar los cursos con palabras comunes
         commonWord = connection.prepareStatement(
         	"SELECT * FROM Course WHERE course LIKE ?"
         );


		} catch (SQLException sqlException) {
         sqlException.printStackTrace();
         System.exit(1);
      	} 
	}


	public List<Faculty> showFaculty(){

		try{
			ResultSet resultSet = showTableFaculty.executeQuery();
			List<Faculty> results = new ArrayList<Faculty>();
			while(resultSet.next()){
				results.add(new Faculty(
					resultSet.getString("faculty_id"),
					resultSet.getString("faculty_name"),
					resultSet.getString("office")
					));
			}
			return results;
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return null;
	}

	public List<Courses> showCourse(){
		try{
			ResultSet resultSet = showTableCourse.executeQuery();
			List<Courses> results = new ArrayList<Courses>();
			while(resultSet.next()){
				results.add(new Courses(
					resultSet.getString("course_id"),
					resultSet.getString("course"),
					resultSet.getString("faculty_id")
				));
			}
			return results;
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
     	}
     	return null;
	}

	public String addFaculty(String faculty_id,String faculty_name, String office){
		
		try{
			verificationIDFaculty.setString(1,faculty_id);

			ResultSet resultSet = verificationIDFaculty.executeQuery();
			int cont = 0;
			while(resultSet.next()){
				cont++;
			}
			if(cont==0){
				insertNewFaculty.setString(1, faculty_id);
				insertNewFaculty.setString(2,faculty_name);
				insertNewFaculty.setString(3,office);
				insertNewFaculty.executeUpdate();
				return "dato ingresado";
			}
			
			//return 1;
			return "dato duplicado";
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return null;
	}


	public String addCourse(String course_id,String course,String faculty_id){
		try{
			verificationIDCourses.setString(1,course_id);
			ResultSet resultSet = verificationIDCourses.executeQuery();
			verificationDataRep.setString(1,faculty_id);
			ResultSet resultSet1 = verificationDataRep.executeQuery();

			int cont1 = 0; int cont2 = 0;
			while(resultSet.next()){
				cont1++;
			}
			while(resultSet1.next()){
				cont2++;
			}
			
			if(cont1==0 && cont2>0){
				insertNewCourses.setString(1,course_id);
				insertNewCourses.setString(2,course);
				insertNewCourses.setString(3,faculty_id);
				insertNewCourses.executeUpdate();
				return "dato ingresado";
			}
			return "dato duplicado";
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return "dato duplicado";
	}


	public String delCourse(String course_id){
		try{	
			deleteCourses.setString(1,course_id);
			deleteCourses.executeUpdate();
			return "registro eliminado";
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return null;
	}

	public String delFaculty(String faculty_id){
		try{
			verificationNewCourses.setString(1,faculty_id);
			ResultSet resultSet1 = verificationNewCourses.executeQuery();
			int cont = 0;
			while(resultSet1.next()){
				cont++;
				if(cont>0){
					String data = resultSet1.getString("course_id");
					deleteCourses.setString(1,data);
					deleteCourses.executeUpdate();
					
				}
			}
			//if(cont==0){return "datos no eliminados";}
			deleteFaculty.setString(1,faculty_id);	
			deleteFaculty.executeUpdate();
			return "datos eliminados";

		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return "no encontrado";
	}

	public String actFaculty(String faculty_id,String faculty_name, String office){
		try{
			//updateFaculty.setString(1,faculty_id);
			updateFaculty.setString(1,faculty_name);
			updateFaculty.setString(2,office);
			updateFaculty.setString(3,faculty_id);
			verificationIDFaculty.setString(1,faculty_id);
			ResultSet resultSet1 = verificationIDFaculty.executeQuery();
			int cont = 0;
			while(resultSet1.next()){
				cont++;
			}
			if(cont==0){return "datos no actualizados -- faculty_id no encontrada";}
			updateFaculty.executeUpdate();
			return "datos actualizados";
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return "datos no actualizados";
	}

	public String actCourse(String course_id,String course){
		try{
			updateCourses.setString(1,course);
			updateCourses.setString(2,course_id);
			verificationIDCourses.setString(1,course_id);
			ResultSet resultSet1 = verificationIDCourses.executeQuery();
			int cont = 0;
			while(resultSet1.next()){
				cont++;
			}
			if(cont==0){return "datos no actualizados -- faculty_id no encontrada";}
			updateCourses.executeUpdate();
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return "Datos actualizados";
	}

	//Permitir la consulta completa de cursos por profesor.
	public List<Courses> showTeacher(String faculty_name){
		try{
			List<Courses> results = new ArrayList<Courses>();
			completeTeacherInfo.setString(1,faculty_name);
			ResultSet resultSet = completeTeacherInfo.executeQuery();
			while(resultSet.next()){
				results.add(new Courses(
					resultSet.getString("course_id"),
					resultSet.getString("course"),
					resultSet.getString("faculty_id")
				));
			}
			return results;
		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return null;
	}

	public List<Courses> likeCourse(String word){
		try{
			palabra = word;
			commonWord.setString(1,word);
			//commonWord.executeQuery();
			List<Courses> results = new ArrayList<Courses>();
			ResultSet resultSet = commonWord.executeQuery();
			while(resultSet.next()){
				results.add(new Courses(
					resultSet.getString("course_id"),
					resultSet.getString("course"),
					resultSet.getString("faculty_id")
				));
			}
			return results;


		}catch (SQLException sqlException) {
         sqlException.printStackTrace();
         //return 0;
     	}
     	return null;
	}

}