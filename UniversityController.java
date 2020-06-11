import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class UniversityController {
	
	public static GeneralQueries queriesRes = new GeneralQueries();
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int option = 0;
		do {
			Menu();
	       option = scanner.nextInt();
	       System.out.println();
	       switch (option) {
	      		case 1:
		        	List<Faculty> dataFactory= queriesRes.showFaculty();
						for(Faculty dat:dataFactory){
							System.out.println(dat);
						}
	        	break;

	        	case 2:
	        		List<Courses> dataCourse = queriesRes.showCourse();
						for(Courses dat:dataCourse){
							System.out.println(dat);
						}
	        	break;

	        	case 3:
	        			registerFacultad();
	        	break;

	        	case 4:
	        			registerCourse();
	        	break;

	        	case 5:
	        			deleteFaculty();
	        	break;

	        	case 6:
	        			deleteCourse();
	        	break;	

	        	case 7:
	        			changeRegFaculty();
	        	break;

	        	case 8:
	        			changeRegCourse();
	        	break;

	        	case 9:
	        			teacherShow();
	        	break;

	        	case 10:
	        			letraComun();
	        	break;
	    	}
	    	System.out.println();
	    } while (option < 12 && option > 0);


	}

	public static void Menu(){
		System.out.println("------------------------------------------------------------");
		System.out.println("|                     Menu                                 |");
		System.out.println("|1.) Mostrar Tabla Facultad                                |");
		System.out.println("|2.) Mostrar Tabla Cursos                                  |");
		System.out.println("|3.) Crear nuevo registro de Facultad                      |");
		System.out.println("|4.) Crear nuevo registro de Curso                         |");
		System.out.println("|5.) Eliminar un registro de Facultad                      |");
		System.out.println("|6.) Eliminar un registro de Curso                         |");
		System.out.println("|7.) Cambiar registro de Facultad                          |");
		System.out.println("|8.) Cambiar registro de Curso                             |");
		System.out.println("|9.) Cursos por profesor                                   |");
		System.out.println("|10.) Cursos con palabras comunes                          |");
		System.out.println("|Para salir pulse cualquier otro numero                    |");
		System.out.println("------------------------------------------------------------");
		System.out.print("Select a Menu Option: ");
	}

	public static void registerFacultad(){
					Scanner scanner = new Scanner(System.in);
					System.out.println("faculty id: "); String idValue = scanner.nextLine();
	        		
	        		System.out.println("faculty name: ");
	        		String fac_name = scanner.nextLine();
	        		System.out.println("office: ");
	        		String office = scanner.nextLine();
	        		System.out.println(queriesRes.addFaculty(idValue,fac_name,office));
	}

	public static void registerCourse(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("course id: ");
	        		String idValue = scanner.nextLine();
	        		System.out.println("course name: ");
	        		String course_name = scanner.nextLine();
	        		System.out.println("faculty id: ");
	        		String fac_id = scanner.nextLine();
	        		System.out.println(queriesRes.addCourse(idValue,course_name,fac_id));
	}

	public static void deleteFaculty(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("faculty id: ");
		String idValue = scanner.nextLine();
		System.out.println(queriesRes.delFaculty(idValue));
	}

	public static void deleteCourse(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("course id: ");
		String idValue = scanner.nextLine();
		System.out.println(queriesRes.delCourse(idValue));
	}

	public static void changeRegFaculty(){
		Scanner scanner = new Scanner(System.in);
					System.out.println("faculty id: "); String idValue = scanner.nextLine();
	        		
	        		System.out.println("faculty name: ");
	        		String fac_name = scanner.nextLine();
	        		System.out.println("office: ");
	        		String office = scanner.nextLine();
	        		System.out.println(queriesRes.actFaculty(idValue,fac_name,office));
	}

	public static void changeRegCourse(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("course id: ");
	        		String idValue = scanner.nextLine();
	        		System.out.println("course name: ");
	        		String course_name = scanner.nextLine();
	        		System.out.println(queriesRes.actCourse(idValue,course_name));
	}

	public static void teacherShow(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("teacher name: ");
	    String idValue = scanner.nextLine();
	    for(Courses dat : queriesRes.showTeacher(idValue)){
	    	System.out.println(dat);
	    }
	}

	public static void letraComun(){
				Scanner scanner = new Scanner(System.in);
		System.out.println("falculty id: ");
	    String idValue = scanner.nextLine();
	    for(Courses dat : queriesRes.likeCourse("%"+idValue+"%")){
	    	System.out.println(dat);
	    }
	}

}
