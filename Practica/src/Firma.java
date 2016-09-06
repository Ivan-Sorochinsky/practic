public class Firma implements Comparable<Firma> {
	// Сущность - Фирма
	// поля - скрыты в классе
	private final static String FIRM_FORMAT_STRING = "Фирма: %-7d | %-10s | %-7d | %-8.2f |"; // формат
																								// записи
																								// о
																								// фирме
	private int id; // id фирмы, ключевое поле
	private String name; // название
	private int rab; // число_сотрудников
	private double zp; // базовая_зарплата
	// конструктор без параметров

	public Firma() {
		this.id = 0;
		this.name = "";
		this.rab = 0;
		this.zp = 0;
	}

	// конструктор с параметрами
	public Firma(int id, String name, int rab, double zp) {
		this.id = id;
		this.name = name;
		this.rab = rab;
		this.zp = zp;
	}

	// методы-геттеры
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getRab() {
		return rab;
	}

	public double getZP() {
		return zp;
	}

	// методы-сеттеры
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRab(int rab) {
		this.rab = rab;
	}

	public void setZP(double zp) {
		this.zp = zp;
	}

	// Переопределяется метод toString класса Object
	// (возвращает строку описания объекта)
	public String toString() {
		return String.format(FIRM_FORMAT_STRING, id, name, rab, zp);

	}

	// Переопределяется метод equals класса Object
	// (задает способ сравнения объектов на равенство,
	// возвращает true, если запускающий объект
	// равен объекту-параметру)
	public boolean equals(Object ob) {
		if (ob == this)
			return true; // ссылки равны – один
							// и тот же объект
		if (ob == null)
			return false; // в метод передана null-ссылка
		if (getClass() != ob.getClass())
			return false; // объекты разных классов
		Firma fr = (Firma) ob; // преобразуем Object в Firma
		return id == fr.id; // id – ключевое поле объекта
	}

	// Переопределяется метод hashCode класса Object
	// Возвращает хэш-код объекта
	// (у равных объектов должны быть равные hash-коды)
	public int hashCode() {
		return 7 * (new Integer(id)).hashCode();
	}

	// Определяем метод СоmpareTo интерфейса Сomporable
	// Для определения естественного порядка перечисления элементов
	public int compareTo(Firma firm) {
		if (id < firm.id)
			return -1;
		else if (id == firm.id)
			return 0;
		else
			return 1;
	}
}
