import java.util.*; 
public class CompSubjectAscSemesterDesc implements Comparator <ExamKey>{
   public int compare (ExamKey ex1, ExamKey ex2){
       String subj1 = ex1.getSubject();
       String subj2 = ex2.getSubject();
       int sem1 = ex1.getSemester();
       int sem2 = ex2.getSemester();
       if (subj1.compareTo(subj2)<0)return -1;
       if (subj1.compareTo(subj2)>0)return 1;
       //шифры предметов равны (один и тот же предмет)
       if (sem1>sem2) return -1;
       if (sem1<sem2) return 1;
       return 0;
   }
   //нет необходимости переопределять метод equals
}
