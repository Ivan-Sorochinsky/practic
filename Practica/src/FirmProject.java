import java.util.*;

public class FirmProject { // Сущность – Проект фирмы
	// поля (скрыты в классе)
	private final static String PROJECT_FORMAT_STRING = "Проект: %-s, %-5d фирмы"; // формат
																					// записи
																					// о
																					// проекте
	private String name; // название проекта
	private List<Firma> firms; // список (набор) фирм
	// конструкторы

	public FirmProject() {
		name = ""; // без названия
		firms = new ArrayList<Firma>(); // создается пустой список
	}

	public FirmProject(String name) {
		this.name = name; // задается название проекта
		firms = new ArrayList<Firma>(); // создается пустой список
	}

	public FirmProject(String name, List list) {
		this.name = name; // задается название проекта
		firms = new ArrayList<Firma>(list); // создается на основе
											// существующего списка
	}

	// метод-сеттер для private-поля
	public void setProjectName(String name) {
		this.name = name;
	}

	// методы-геттеры для private-полей
	public String getProjectName() {
		return name;
	}

	public List<Firma> getFirm() {
		return firms;
	}

	// Переопределяем метод toString класса Object
	// возвращает строку описания объекта проект фирмы:
	public String toString() {
		return String.format(PROJECT_FORMAT_STRING, name, getFirmNum());
	}

	// Запросы на вставку, удаление, изменение данных:
	public boolean addFirm(Firma firm) {
		// Добавить фирму в список проектов
		// (фирму нельзя добавить, если в проекте уже есть фирма
		// с таким же id)
		if (getFirm(firm.getId()) != null)
			return false; // дополнительная
							// программная проверка
		if (firms.add(firm))
			return true;
		else
			return false;
	}

	public boolean delFirm(int id) {
		// Удалить фирму из списка проекта
		if (firms.remove(new Firma(id, "", 0, 0)))
			return true;
		else
			return false;
	}

	// запросы на выборку данных:
	public Firma getFirm(int id) {
		// Возвращает фирму с заданным номером (id):
		for (Firma firm : firms)
			if (firm.getId() == id)
				return firm; // если фирма найдена
		return null; // фирма не найдена
	}

	public int getFirmNum() {
		// Возвращает число фирм в проекте
		return firms.size();
	}

	public double avgZP() {
		// Возвращает среднюю заработную плату по проекту
		int num = firms.size();
		if (num == 0)
			return 0;
		double avg = 0;
		for (Firma firm : firms)
			avg = avg + firm.getZP();
		return avg / num;
	}

	public FirmProject aboveAvgZP() {
		// Вернуть фирмы, у которых зарплата выше среднего
		double avg = avgZP();
		FirmProject project = new FirmProject(name + ": фирмы, у которых зарплата ниже среднего - " + avg);
		// конкатенация на неизменяемых строках - плохое решение,
		// покажем, как избавиться от нее, в методе between
		// для просмотра (перечисления) объектов списка
		// используем цикл for-each
		for (Firma firm : firms)
			if (firm.getZP() < avg)
				project.addFirm(firm);
		return project;
	}

	public FirmProject betweenZP(double b1, double b2) {
		// Вернуть фирмы с зарплатой в диапазоне [b1,b2]
		// Избавляемся от конкатенации при формировании имени проекта
		FirmProject project = new FirmProject(
				String.format("%s: фирмы, у которых зарплата в диапазоне от %4.2f до %4.2f", name, b1, b2));
		// для просмотра (перечисления) объектов списка
		// используем итератор
		Iterator<Firma> iter = firms.iterator();
		while (iter.hasNext()) {
			Firma firm = iter.next();
			if ((firm.getZP() >= b1) && (firm.getZP() <= b2))
				project.addFirm(firm);
		}
		return project;
	}

	// Запросы на сортировку данных
	// Для сортировки списков List используются статические
	// методы sort, определенные в классе Collections
	public FirmProject sort() {
		// Cортировка фирм в естественном порядке
		// Естественный порядок задает метод CompareTo,
		// переопределенный в классе Firma
		FirmProject project = new FirmProject(name, firms);
		Collections.sort(project.firms);
		return project;
	}

	public FirmProject sort(Comparator comp) { // coртировка фирм
		// по правилу, задаваемому компаратором comp
		FirmProject project = new FirmProject(name, firms);
		Collections.sort(project.firms, comp);
		return project;
	}

	// Запрос на вывод данных
	public void putFirmProject() {
		// Вывод проекта фирм в окно терминала
		System.out.println(name); // имя проекта
		System.out.printf("%3s%11s%23s%21s%22s\n", "№", "ID", "Название", "Число сотрудников", "Базовая зарплата"); // заголовки
																												// столбцов
		int i = 1;
		for (Firma firm : firms) {
			System.out.printf("  %-7d  %-15d  %-18s  %-18d  %-10.2f\n", i, firm.getId(), firm.getName(), firm.getRab(), firm.getZP());
			i = i + 1;
		}
	}

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
} // class
