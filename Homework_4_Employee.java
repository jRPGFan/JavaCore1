public class Homework_4_Employee {
    //Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
    private String fullName;
    private String function;
    private String telephoneNumber;
    private int salary;
    private int age;
    //** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.
    private static int employeeIDCounter = 0;
    private int employeeID;

    //Конструктор класса должен заполнять эти поля при создании объекта;
    public Homework_4_Employee(String fullName, String function, String telephoneNumber, int salary, int age){
        this.fullName = fullName;
        this.function = function;
        this.telephoneNumber = telephoneNumber;
        this.salary = salary;
        this.age = age;
        this.employeeID = ++employeeIDCounter;
    }

    //Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
    public String getFullName() { return fullName; }

    public String getFunction() {
        return function;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public int getEmployeeID() { return employeeID; }

    public void getFullEmployeeInfo(){
        System.out.println("ID: " + employeeID + "\nФИО: " + fullName + "\nДолжность: "+ function +
                "\nТелефон: "+ telephoneNumber + "\nЗарплата: " + salary + "\nВозраст: " + age);
        System.out.println();
    }
}
