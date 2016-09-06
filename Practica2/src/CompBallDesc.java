import java.util.*; 
//Надежность библиотек Java усыпляет. Примеры данного проекта показывают, 
//что в Java все таки есть способы «стрельнуть себе в ногу».
public class CompBallDesc implements Comparator <ExamKey>{
   // Как же организовать сортировку карты по значениям,
   // если (по определению) она сортируется по ключам?
   // Нужно получить ссылку на сортируемый набор, чтобы
   // через его ключи получить доступ к значениям!
   Map <ExamKey, String> registr;
   public CompBallDesc (Map <ExamKey, String> registr){
      this.registr=registr;
   }   
   public int compare (ExamKey ex1, ExamKey ex2){
      int ball1=ExamRegister.transMark(registr.get(ex1));
      int ball2=ExamRegister.transMark(registr.get(ex2));
      if (ball1>ball2) return -1;
      return 1;
    //Метод не должен никогда возвращать ноль.
    //В случае, когда он вернул бы ноль при одинаковых значениях 
    //анализируемых неключевых полей, соответствующая запись об экзамене
    //не была бы добавлена в карту, являющуюся результатом сортировки,
    //т.к. считалось бы, что такой объект там уже есть.
   }    
   //нет необходимости переопределять метод equals
}