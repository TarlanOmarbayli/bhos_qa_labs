package my_classroom;
public class Classroom {
    int entry_year;
    String name_of_classroom;
    int total_number_of_students;
    int total_number_of_courses;
    int minimum_credits_per_semester;
    String name_of_curator;

    public Classroom(int entry_year, String name_of_classroom, int total_number_of_students, int total_number_of_courses, int minimum_credits_per_semester, String name_of_curator){
       this.entry_year = entry_year;
       this.name_of_classroom = name_of_classroom;
       this.total_number_of_students = total_number_of_students;
       this.total_number_of_courses = total_number_of_courses;
       this.minimum_credits_per_semester = minimum_credits_per_semester;
       this.name_of_curator = name_of_curator;
    }
    public String getIdOfClass(){
        return this.name_of_classroom + "-" + this.entry_year;
    }
    public int getEntry_year(){
        return this.entry_year;
    }
    public String getName_of_classroom(){
        return this.name_of_classroom;
    }
    public String getName_of_curator(){
        return this.name_of_curator;
    }
    public int getTotal_number_of_students(){
        return this.total_number_of_students;
    }
    public int getTotal_number_of_courses(){
        return this.total_number_of_courses;
    }
    public int getMinimum_credits_per_semester(){
        return this.minimum_credits_per_semester;
    }
    public int getGraduationYear(){
        return this.entry_year + 5;
    }
    public int getMinimumTotalCredits(){
        return this.minimum_credits_per_semester * 8;
    }

    public static void main(String[] args){
        Classroom infosec = new Classroom(2018,"InfoSec",25,24,240,"Khayyam Masiyev");
        System.out.println((infosec.getEntry_year()));
        System.out.println(infosec.getName_of_classroom());
        System.out.println(infosec.getIdOfClass());
        System.out.println(infosec.getGraduationYear());
        System.out.println(infosec.getMinimum_credits_per_semester());
        System.out.println(infosec.getMinimumTotalCredits());
        System.out.println(infosec.getTotal_number_of_students());
        System.out.println(infosec.getTotal_number_of_courses());
        System.out.println(infosec.getName_of_curator());

    }


}
