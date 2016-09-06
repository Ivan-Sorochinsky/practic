import java.util.*;
public class FirmProject { // Сущность - Проект фирмы
	//поля
	private final static String project_FORMAT_STRING = 
                     "Проект: %-s, %-5d фирмы"; //формат записи о проекте
	private String name; //название проекта
	private Set <Firma> firms; // список (набор) фирм
	// конструкторы
	public FirmProject(){
	  name=""; //без названия
	  firms = new TreeSet <Firma>(); //создается пустой набор
    }
	public FirmProject(String name){
	  this.name=name; //задается название проекта
	  firms = new TreeSet <Firma>(); //создается пустой набор
    }
    public FirmProject(String name, Comparator comp){
	  this.name=name; //задается название проекта
	  firms = new TreeSet <Firma>(comp); //создается пустой набор,
            // в котором ссылки на добавляемые объекты будут 
            // упорядочиваться по правилам компаратора comp
    }
    public FirmProject(String name, Set set){
	  this.name=name; //задается название проекта
	  firms = new TreeSet <Firma>(set); //создается набор на основе 
                                                                                 // существующего набора set
    }
    //метод-сеттер для private-поля
    public void setProjectName(String name)
        {this.name=name;}
    //методы-геттеры для private-полей
    public String getProjectName(){return name;}
    public Set <Firma> getFirms(){
        return firms;
    }
    //Переопределяем метод toString класса Object
    //возвращает строку описания объекта Группа фирм:
    public String toString(){
         return String.format(project_FORMAT_STRING,name,getFirmNum());  
    }
    //Запросы на вставку  и  удаление  данных:
    public boolean addFirm(Firma firm){
      //Добавить фирму в список проекта
	    //фирма не будет вставлен, если в наборе уже есть фирма
	    //таким же id 
	    if (firms.add(firm)) return true;
	    else return false;
    }
    

public boolean delFirm(int id){
   //Удалить фирму из списка проекта
        if (firms.remove(new Firma(id,"",0,0))) return true;
        else return false;
    }
    // Запросы на выборку данных:
    public Firma getFirm (int id){
       // возвращает фирму с заданным номером зачетки (id):
        for (Firma firm:firms)
           if (firm.getId() == id) return firm; // если фирма найден
        return null; // фирма не найден
    } 
    public int getFirmNum(){
    //Возвращает число фирм в группе    
        return firms.size();
    }    
    public double avgZP(){
       //Возвращвет средний зарплата по группе
	    int num=firms.size();
	    if (num==0) return 0;
	    double avg=0;
		for (Firma firm:firms)
		    avg=avg+firm.getZP();
		return avg/num;
	}
   
   public FirmProject aboveAvgZP(){
       //Возвращает фирм, у которых зарплата выше среднего
       double avg=avgZP();
       FirmProject project = new FirmProject (name+
                       ": Фирмы, у которых зарплата выше среднего - " + avg);
       // конкатенация на неизменяемых строках - плохое решение, 
       // покажем, как избавиться от нее, в методе between                
       //для просмотра (перечисления) объектов списка 
       //используем цикл for-each                 
       for (Firma firm:firms)
           if (firm.getZP()>avg) project.addFirm(firm);
       return project;
   }
   
   public FirmProject betweenZP(double b1, double b2){// запрос:
     //Возвращает фирм с зарплатаом в диапазоне [b1,b2]
      //Избавляемся от конкатенации при формировании имени проекта    
      FirmProject project = new FirmProject (
    		  String.format("%s: фирмы, у которых зарплата в диапазоне от %4.2f до %4.2f", name, b1, b2));
	  // для просмотра (перечисления) объектов списка
      // используем итератор
      Iterator <Firma> iter=firms.iterator();
       while (iter.hasNext()){
          Firma firm=iter.next(); 
          if ((firm.getZP()>=b1)&&(firm.getZP()<=b2))project.addFirm(firm);
      }    
      return project;
   }
   //запросы на сортировку данных
  public FirmProject sortZPAsc(){
     FirmProject project = new FirmProject(name+
                " (сортировка по возрастанию зарплаты)", new CompZPAsc());
     for (Firma firm:firms) project.addFirm(firm); // при добавлении 
     // в набор производится упорядочивание ссылок на объекты по
     // правилу, задаваемому  компаратором (сортировка)
     return project;  
   }
  public FirmProject sortZPDesc(){
     FirmProject project = new FirmProject(name+
            " (сортировка по убыванию зарплатаа)", new CompZPDesc());
     for (Firma firm:firms) project.addFirm(firm); // при добавлении 
     // в набор производится упорядочивание ссылок на объекты по
     // правилу, задаваемому  компаратором (сортировка)
     return project;
   } 
   public FirmProject  sortNameAscZPDesc (){
     FirmProject project = new FirmProject(name+
        " (сортировка по возрастанию имени и убыванию зарплаты)",
         new CompNameAscZPDesc());
     for (Firma firm:firms) project.addFirm(firm); // при добавлении 
     // в набор производится упорядочивание ссылок на объекты по
     // правилу, задаваемому  компаратором (сортировка)
     return project;
   } 
   // Запрос на вывод данных
   public void putFirmProject(){
     //Вывод проекта фирм в окно терминала
	   System.out.println(name); // имя проекта
		System.out.printf("%3s%11s%23s%21s%22s\n", "№", "ID", "Название", "Число сотрудников", "Базовая зарплата"); // заголовки
			
		int i = 1;
		for (Firma firm : firms) {
			System.out.printf("  %-7d  %-15d  %-18s  %-18d  %-10.2f\n", i, firm.getId(), firm.getName(), firm.getRab(), firm.getZP());
			i = i + 1;
   }    
  } // putFirmProject   
   
 //Обновить (в теории БД – update)  id у объекта с заданным id (предусмотреть проверку на уникальность нового id)

   public boolean UpdateId(int id1, int newId){ // 1й метод, обновление Id 
   if (getFirm(id1)!=null && !firms.contains(new Firma(newId,"",0,0))){ 
   getFirm(id1).setId(newId); 
   System.out.println("Id "+id1+" был обновлён на "+newId); 
   return true; 
   } 
   else{System.out.println("Id не был обновлён"); return false; } 
   } 

   //2й метод, удалить фирмы у которых значение числового вещественного поля ниже среднего
   public FirmProject DelObject(){
   	double avg = avgZP();// Возвращает среднюю заработную плату по проекту
   	FirmProject project = new FirmProject(name + ": фирмы, у которых зарплата выше среднего среднего: " + avg+" остальные удаляем");
   	for (Firma firm : firms){
   		project.addFirm(firm);
   		if (firm.getZP() < avg){
   			int c = firm.getId();
   			project.delFirm(c);
   		}
   	}
   	return project;
   }

   public FirmProject Filtr (String str){ //3й метод, вывод слов начинающихся заданным буквосочетанием 
   FirmProject project = new FirmProject (
   		String.format ("%s: Фирмы начинающиеся заданным буквосочетанием: %s", name, str)); 
   Iterator <Firma> iter=firms.iterator(); 
   while (iter.hasNext()){ 
   Firma firm = iter.next (); 
   if (firm.getName().indexOf(str)==0){
   project.addFirm(firm); 
   }
   }
   return project;
   }
} //class
