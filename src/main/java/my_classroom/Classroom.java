package my_classroom;



public class Classroom {
    int entryYear;
    String nameOfClassroom;
    int totalNumberOfStudents;
    int totalNumberOfCourses;
    int minimumCreditsPerSemester;
    String nameOfCurator;

    public Classroom(int entryYear, String nameOfClassroom, int totalNumberOfStudents, int totalNumberOfCourses, int minimumCreditsPerSemester, String nameOfCurator){
       this.entryYear = entryYear;
       this.nameOfClassroom = nameOfClassroom;
       this.totalNumberOfStudents = totalNumberOfStudents;
       this.totalNumberOfCourses = totalNumberOfCourses;
       this.minimumCreditsPerSemester = minimumCreditsPerSemester;
       this.nameOfCurator = nameOfCurator;
    }
    public String getIdOfClass(){
        return this.nameOfClassroom + "-" + this.entryYear;
    }
    public int getEntryYear(){
        return this.entryYear;
    }
    public String getNameOfClassroom(){
        return this.nameOfClassroom;
    }
    public String getNameOfCurator(){
        return this.nameOfCurator;
    }
    public int getTotalNumberOfStudents(){
        return this.totalNumberOfStudents;
    }
    public int getTotalNumberOfCourses(){
        return this.totalNumberOfCourses;
    }
    public int getMinimumCreditsPerSemester(){
        return this.minimumCreditsPerSemester;
    }
    public int getGraduationYear(){
        return this.entryYear + 5;
    }
    public int getMinimumTotalCredits(){
        return this.minimumCreditsPerSemester * 8;
    }

    public static void main(String[] args){
    }
}
