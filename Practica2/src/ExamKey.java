public class ExamKey implements Comparable <ExamKey>{// Сущность - Ключ экзамена
  //поля - скрыты в классе
  private final static String EXKEY_FORMAT_STRING = 
       "Фирма: %1d | Деталь: %1s | Количество: %1d |"; //формат записи о Ключе экзамена
  private int idStudent; //номер зачетной книжки студента
  private String subject; //шифр предмета
  private int semester; //номер семестра
  // конструктор без параметров
  public ExamKey(){
      this.idStudent=0; this.subject="";
      this.semester=0;
  }
  // конструктор с параметрами
  public ExamKey(int idStudent, String Subject, int semester){
      this.idStudent=idStudent; this.subject=Subject; 
      this.semester=semester;
  }
  //методы-геттеры
  public int getIdStudent(){return idStudent;}
  public String getSubject(){return subject;}
  public int getSemester(){return semester;}
  //методы-сеттеры    
  public void setIdStudent(int idStudent){this.idStudent = idStudent;}
  public void setSubject(String subject){this.subject = subject;}
  public void setSemester(int semester){this.semester = semester;}
  //Переопределяется метод toString класса Object
  //(возвращает строку описания объекта) 
  public String toString(){
        return String.format(EXKEY_FORMAT_STRING,idStudent,subject,semester);  
  }
  //Переопределяется метод equals класса Object
  //(задает способ сравнения объектов на равенство,
  //возвращает true, если запускающий объект равен объекту-параметру)
  public boolean equals (Object ob){ 
      if (ob==this) return true; // ссылки равны – один 
                                                 // и тот же объект
      if (ob==null) return false; //в метод передана null-ссылка
      if (getClass()!=ob.getClass())return false; //объекты разных классов
      ExamKey ek=(ExamKey)ob; // преобразуем Object в Student
      return (idStudent == ek.idStudent)&&(subject.equals(ek.subject))
              &&(semester==ek.semester); 	//true, если каждое из полей
      //текущего (запускающего) объекта равно соответствующему полю 
      //объекта-параметра         
 }
 //Переопределяется метод hashCode класса Object
 //Возвращает хэш-код объекта
 //(у равных объектов должны быть равные hash-коды)
 public int hashCode(){
       return 7* (new Integer(idStudent)).hashCode()+
              11 * subject.hashCode()+
              13 * (new Integer(semester)).hashCode();
 }
 
 //Определяем метод СоmpareTo интерфейса Сomporable
 //Для определения порядка (естественного) перечисления элементов
 public int compareTo(ExamKey ek){
     //по возрастанию номера зачетки,
     //если номера зачетки одинаковые -
     //по возрастанию шифра предмета,
     //если шифры предмета одинаковые -
     //по возрастанию семестра
     if (idStudent < ek.idStudent) return -1;
     if (idStudent > ek.idStudent) return 1;
     //номера зачеток равны (один и тот же студент)
     if  (subject.compareTo(ek.subject)<0) return -1;
     if  (subject.compareTo(ek.subject)>0) return 1;
     //предметы равны (один и тот же предмет)
     if (semester < ek.semester) return -1;
     if (semester > ek.semester) return 1;
     return 0; //семестры равны     
 }    
}