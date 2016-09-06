import java.util.*; 
public class CompIdAscSemesterDesc implements Comparator <ExamKey>{
   public int compare (ExamKey ex1, ExamKey ex2){
       int id1 = ex1.getIdStudent();
       int id2 = ex2.getIdStudent();
       int sem1 = ex1.getSemester();
       int sem2 = ex2.getSemester();
       if (id1<id2) return -1;
       if (id1>id2) return 1;
       //номера зачеток совпадают (один и тот же студент)
       if (sem1>sem2) return -1;
       if (sem1<sem2) return 1;
       return 0;
   }
   //нет необходимости переопределять метод equals
}
