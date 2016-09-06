import java.util.*;
public class ExamRegister { // Сущность -  Зачетно-экзаменационная 
    //ведомость (Группа экзаменов)
	//Поля
	//Статические элементы разделяются всеми объектами данного класса
	//т.е. являются общими для всех объектов
	//Инициализируем список возможных оценок (класс Arrays 
	//находится в пакете java.util)и является членом  Java Collections Framework
	private final static List <String> MARKS = Arrays.asList("1","2", "3","4"); 
	private final static String EXREG_FORMAT_STRING = "\"Детали: %s, количество: %1d\""; //формат записи о группе экзаменов
	private String name; //название группы экзаменов (ведомости)
	private Map <ExamKey, String> register; // карта (ведомость) экзаменов
	// конструкторы
	public ExamRegister(){
	  name=""; //без названия
	  register = new TreeMap <ExamKey, String>(); //создается пустая карта
    }
	public ExamRegister(String name){
	  this.name=name; //задается название ведомости 
	  register = new TreeMap <ExamKey, String>(); //создается пустая карта
    }
    public ExamRegister(String name, Comparator comp){
	  this.name=name; //задается название ведомости
	  register = new TreeMap <ExamKey, String> (comp);//создается пустая карта,
            // в которой ссылки на добавляемые объекты-ключи будут 
            // упорядочиваться по правилам компаратора comp    
    }
    //метод-сеттер для private-поля
    public void setRegisterName(String name)
        {this.name=name;}
    //методы-геттеры для private-полей
    public String getRegisterName(){return name;}
    public Map <ExamKey, String> getRegister(){
        return register;
    }
    //Переопределяем метод toString класса Object
    //возвращает строку описания объекта Группа студентов:
    public String toString(){
         return String.format(EXREG_FORMAT_STRING,name,size());  
    }
    //Запросы на вставку  и  удаление  данных:
    public boolean addExam(ExamKey ek, String mark){
	 //добавить экзамен (ключ и значение(оценку)) в карту
	    //экзамен не будет вставлен, если в наборе уже есть экзамен
	    //с таким же ключом
	        if (register.containsKey(ek)) return false;
	        if (!MARKS.contains(mark)) return false;
	        register.put(ek,mark);
	        return true;
	}
    public boolean delExam(ExamKey ek){
        //удалить экзамен из ведомости по ключу 
        if (register.containsKey(ek)){register.remove(ek); return true;}
        else return false;
    }  
   public boolean delExam(int idStudent){
    //удалить все экзамены конкретного студента
     Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
     Iterator <Map.Entry <ExamKey,String>> it = setE.iterator();
     int i=0; //счетчик найденных экзаменов
     while (it.hasNext()){
        Map.Entry <ExamKey,String> keyVal = it.next();
        if (keyVal.getKey().getIdStudent()==idStudent){
            it.remove();
            i=i+1;
        }    
     }
     if (i==0) return false;
     return true;
     //Удаление входа (пары ключ-значение)из набора setE методом 
     //remove() итератора it влечет за собой удаление этого входа 
     //из карты register (формируется неявный итератор для карты). 
     //Любой другой способ удаления входа 
     //при циклическом просмотре входов карты вызовет ошибку
     //типа ConcurrentModificationException.
     //Надежность библиотек Java усыпляет. Примеры данного проекта показывают, 
     //что в Java все таки есть способы «стрельнуть себе в ногу».
   }
    public boolean updateExam(ExamKey ek,  String mark){
        //обновить оценку на ключе (студент пересдал экзамен)
       if (!register.containsKey(ek)) return false;
       if (!MARKS.contains(mark)) return false;
       register.put(ek, mark);
       return true;
    }   
    // Запросы на выборку данных:
    public int size(){
    //Возвращает число экзаменов в ведомости   
        return register.size();
    }
    public ExamRegister selectStudentData(int idStud){
       //Возвращает карту экзаменов заданного студента
       ExamRegister subExReg = new ExamRegister ( String.format("%s: выборка по фирме %7d",
                                               name,idStud));// создаем (под)ведомость
       Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
       for (Map.Entry <ExamKey, String> keyVal:setE)
            if (keyVal.getKey().getIdStudent()==idStud) 
                subExReg.register.put(keyVal.getKey(),keyVal.getValue());
       return  subExReg;        
   }
   
   public ExamRegister selectSubjectData(String subject){
       //Возвращает карту экзаменов по заданному предмету
       ExamRegister subExReg = new ExamRegister ( String.format("%s: выборка по детале %s",
                                               name,subject
                                               ));// создаем (под)ведомость
       Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
       for (Map.Entry <ExamKey, String> keyVal:setE)
            if (keyVal.getKey().getSubject().equals(subject)) 
                subExReg.register.put(keyVal.getKey(),keyVal.getValue());
       return  subExReg;        
   }
   public ExamRegister selectStudentSemestrData(int idStud, int semester){
       //Возвращает карту экзаменов заданного студента в заданном семестре
       ExamRegister subExReg = new ExamRegister (
             String.format("%s: выборка по фирме %1d и количеству %1d",
                 name,idStud, semester));// создаем (под)ведомость
       Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
       for (Map.Entry <ExamKey, String> keyVal:setE)
            if ((keyVal.getKey().getIdStudent()==idStud)&&(keyVal.getKey().getSemester()==semester))
                subExReg.register.put(keyVal.getKey(),keyVal.getValue());
       return  subExReg;        
   }
   public static int transMark(String mark){
        if (mark=="отлично") return 5;
        if (mark=="хорошо") return 4;
        if (mark=="удовлетворительно") return 3;
        return 0;
   }    
   public int selectSumBallStud(int idStud){
       //Возвращает суммарный балл для конкретного студента 
       int sum = 0;
       //Получаем выборку(карту) по заданному студенту
       ExamRegister subReg = selectStudentData(idStud);
       //Получаем из карты набор значений (оценок)
       Collection <String> v = register.values();
       //Суммируем оценки
       for (String mark: v) sum+=transMark(mark);
       return sum;
	}
    //запросы на сортировку данных
    public ExamRegister sortIdAscSemesterDesc(){
      //По возрастанию номера зачетки  
      ExamRegister subExReg = new ExamRegister(String.format(
          "%s:\n\t сортировка по возрастанию номера зачетки и убыванию семестра",
           name),new CompIdAscSemesterDesc());// создаем новую ведомость
      Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
      for (Map.Entry <ExamKey, String> keyVal:setE)
         subExReg.addExam(keyVal.getKey(), keyVal.getValue());// при добавлении 
     // в карту производится упорядочивание ссылок на объекты-ключи по
     // правилу, задаваемому  компаратором (сортировка)
     return  subExReg;   
   }
   public ExamRegister sortSubjectAscSemesterDesc(){
      //По возрастанию шифра предмета  
      ExamRegister subExReg = new ExamRegister(String.format(
          "%s:\n\t сортировка по возрастанию шифра предмета и убыванию семестра",
          name),new CompSubjectAscSemesterDesc());// создаем новую ведомость
      Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
      for (Map.Entry <ExamKey, String> keyVal:setE)
         subExReg.addExam(keyVal.getKey(), keyVal.getValue());
      return  subExReg;   
   }
   public ExamRegister sortBallDesc (){
     //По убыванию балла  
      ExamRegister subExReg = new ExamRegister(String.format(
                        "%s:\n\t сортировка по убыванию балла",
                         name),new CompBallDesc(this.register));// создаем новую ведомость
      Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
      for (Map.Entry <ExamKey, String> keyVal:setE)
         subExReg.addExam(keyVal.getKey(), keyVal.getValue());
      return  subExReg;   
   }
   // Запрос на вывод данных
   public void putExamRegister(){
     //Вывод экзаменационной ведомости в окно терминала
     System.out.println(name); //название ведомости
       System.out.printf("%5s%17s%19s%15s%15s\n", 
             "Номер","Номер зач. кн.","Шифр предмета","Семестр","Оценка"); //заголовки столбцов
     int i=1; 
     Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
     for (Map.Entry <ExamKey, String> keyVal:setE){
        ExamKey key=keyVal.getKey();//получили ключ очередноговхода карты
        System.out.printf("  %-7d  %-15d  %-16s  %7d  %20s\n",
             i, key.getIdStudent(), key.getSubject(), key.getSemester(), keyVal.getValue());
        i=i+1;
   }    
  } // putStudGroup         
} //class
