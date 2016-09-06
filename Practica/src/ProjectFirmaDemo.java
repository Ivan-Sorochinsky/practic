public class ProjectFirmaDemo { // Взаимодействие фирм и проектов
	public static void main(String[] args) {
		// Создаем проекты фирм
		FirmProject pr1 = new FirmProject("Festo");
		FirmProject pr2 = new FirmProject("Kepler");
		// в массив args при запуске метода main (String[ ] args)
		// ввели 10 фирм: по 5 для каждой группы.
		int j = 0; // индекс массива args
		// добавление фирм в первую группу
		for (int i = 0; i < 5; i++) {
			int id = Integer.valueOf(args[j]);
			j++; // получен int-эквивалент из String
			String name = args[j];
			j++;
			int rab = Integer.valueOf(args[j]);
			j++;
			// получаем double-эквивалент из String
			double ZP = Double.valueOf(args[j]);
			j++;
			// добавить фирм
			pr1.addFirm(new Firma(id, name, rab, ZP));
		}
		// добавление фирм во вторую группу
		for (int i = 0; i < 5; i++) {
			int id = Integer.valueOf(args[j]);
			j++;
			String name = args[j];
			j++;
			int rab = Integer.valueOf(args[j]);
			j++;
			double ZP = Double.valueOf(args[j]);
			j++;
			// добавить фирм
			pr2.addFirm(new Firma(id, name, rab, ZP));
		}
		// Выборка данных
		// Выводим всевозможные списки фирм
		// Выводим основной список без сортировки
		System.out.println("Список фирм участвующих в проекте (без сортировки)");
		pr1.putFirmProject();
		// пытаемся вставить фирм с тем же номером id
		System.out.println("Попытка добавить фирму: 3234, Скворцово, 76, 5000.0");
		pr1.addFirm(new Firma(3234, "Скворцово", 76, 5000.0f));
		// снова выводим основной список
		// (теперь с естественным порядком сортировки)
		System.out.println("Список фирм участвующих в проекте (с естественным порядком сортировки)");
		pr1.sort().putFirmProject();
		// другие списки:
		System.out.println("Список фирм участвующих в проекте (с сортировкой по возрастанию зарплаты)");
		pr1.aboveAvgZP().sort(new CompZPAsc()).putFirmProject();
		System.out.println("Список фирм участвующих в проекте (с сортировкой по убыванию зарплаты)");
		pr1.betweenZP(3000f, 6000f).sort(new CompZPDesc()).putFirmProject();
		// удаление фирм по id
		pr1.delFirm(3234);
		// выводим список группы pr1 после удаления фирм
		System.out.println("После удаления фирмы c id=3234:");
		System.out.println("Список фирм участвующих в проекте (с естественным порядком сортировки)");
		pr1.putFirmProject();
		// Выводим списки фирм для второй группы
		System.out.println("Список фирм участвующих в проекте (с сортировкой по возрастанию названия и");
		System.out.println(" убыванию зарплаты)");
		pr2.sort(new CompNameAscZPDesc()).putFirmProject();
		// другие списки для второй группы:
		System.out.println("Список фирм участвующих в проекте (с сортировкой по возрастанию зарплаты)");
		pr2.aboveAvgZP().sort(new CompZPAsc()).putFirmProject();
		System.out.println("Список фирм участвующих в проекте (с сортировкой по убыванию зарплаты)");
		pr2.betweenZP(30f, 8000f).sort(new CompZPDesc()).putFirmProject();
		// Проверяем,есть ли во первой проекте фирма
		// с заданным номером id
		int n = 34;
		Firma s1 = pr1.getFirm(n);
		if (s1 == null)
			System.out.println("В проекте " + pr1.getProjectName() + " нет фирмы с номером  " + n);
		else
			System.out.println(s1);
		n = 354;
		s1 = pr1.getFirm(n);
		if (s1 == null)
			System.out.println("В проекте " + pr1.getProjectName() + " нет фирмы с номером  " + n);
		else
			System.out.println(s1);
		// Проверяем,есть ли во второй проекте фирма с заданным
		// номером id
		n = 53349;
		s1 = pr2.getFirm(n);
		if (s1 == null)
			System.out.println("В проекте " + pr1.getProjectName() + " нет фирмы с номером  " + n);
		else
			System.out.println(s1);
		pr1.UpdateId(34, 24);            //1й метод индивидуального задания
		pr1.Filtr("Бе").putFirmProject();//3й метод индивидуального задания
		pr1.DelObject().putFirmProject();//2й метод индивидуального задания
		
	}
}