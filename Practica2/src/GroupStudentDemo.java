public class DisciplineKey implements Comparable <DisciplineKey>{
  private final static String DisciplinaKey_FORMAT_STRING = 
"Дисциплина: %1d | Преподователь: %1s | Номер семестра: %1d |"; 
  private int id; 
  private String login; 
  private String semester; 
  public DisciplineKey(){
      this.id=0; this.login="";
      this.semester="";
  }
  public DisciplineKey(int id, String login, String semester){
      this.id=id; this.login=login; 
      this.semester=semester;
  }
  public int getId(){return id;}
  public String getlogin(){return login;}
  public String getSemester(){return semester;}   
  public void setId(int id){this.id = id;}
  public void setTeam(String login){this.login = login;}
  public void setMonth(int month){this.semester = semester;}
  public String toString(){
        return String.format(DisciplinaKey_FORMAT_STRING,id,login,semester);  
  }
  public boolean equals (Object ob){ 
      if (ob==this) return true;                                           
      if (ob==null) return false; 
      if (getClass()!=ob.getClass())return false; 
      DisciplineKey ek=(DisciplineKey)ob; 
      return (id == ek.id)&&(login.equals(ek.login))
              &&(semester==ek.semester); 	
 }
 public int hashCode(){
       return 7* (new Integer(id)).hashCode()+
              11 * login.hashCode()+
              13 * (new Integer(semester)).hashCode();
 }
 public int compareTo(DisciplineKey ek){
     if (id < ek.id) return -1;
     if (id > ek.id) return 1;
     if  (login.compareTo(ek.login)<0) return -1;
     if  (login.compareTo(ek.login)>0) return 1;
     if (semester < ek.semester) return -1;
     if (semester> ek.semester) return 1;
     return 0;    
 }    
}