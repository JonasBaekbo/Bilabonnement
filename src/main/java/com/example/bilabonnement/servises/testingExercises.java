package com.example.bilabonnement.servises;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;

import java.util.ArrayList;
import java.util.List;

public class testingExercises {
    private final IRepository repo;
    /*public testingExercises(IRepository<Employee> repo ){
        this.repo = repo;
    }*/
    public testingExercises(IRepository<Department> repo ){
        this.repo = repo;
    }
    //Exercise 1
    public String ReturnTimeOfDay(int time){
    if (time > 4 && time <=11){
        return "morning";
    }else if(time >11 && time <=15){
        return "afternoon";
    }else if(time >15 && time <=21){
        return "evening";
    }else{
        return "night";
    }
    }

    //Exercise 2
    public Boolean IsDepartmentNumberValid(int depNo){
    if((String.valueOf(depNo).length() == 2 || String.valueOf(depNo).length() == 3)&& depNo % 10 == 0 ){
        return true;
    }else{
        return false;
    }
    }
    //Exercise 3
    public Boolean IsEmployeeCommissionValid(Employee emp){
    if (emp.getEmp_commission() > emp.getEmp_salary()){
        return false;
    }else{
        return true;
    }
    }
    //Exercise 4
    public Boolean DoesEmployeeHaveAboveAverageSalary(Employee emp){
        EmployeeRepository EmployeeRepo = new EmployeeRepository();
        ArrayList<Employee> employeeList = (ArrayList<Employee>) EmployeeRepo.getAllEntities();
        int averageSalary = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            averageSalary += employeeList.get(i).getEmp_salary();
        }
        averageSalary = averageSalary/ employeeList.size();
        if (emp.getEmp_salary() > averageSalary){
            return true;
        }else{
        return false;

        }

    }

    //SET 2

    //Exercise 4.2
    public Boolean DoesEmployeeHaveAboveAverageSalaryUsingMock(Employee emp){
        ArrayList<Employee> employeeList = (ArrayList<Employee>) repo.getAllEntities();
        int averageSalary = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            averageSalary += employeeList.get(i).getEmp_salary();
        }
        averageSalary = averageSalary/ employeeList.size();
        if (emp.getEmp_salary() > averageSalary){
            return true;
        }else{
            return false;

        }

    }


    //Exercise 1
    public int FindHighestDepartmentNumber(){
        ArrayList<Department> DepartmentList = (ArrayList<Department>) repo.getAllEntities();
        int HighestDepartmentNumber = 0;
        for (int i = 0; i < DepartmentList.size(); i++) {
            if (HighestDepartmentNumber < DepartmentList.get(i).getDeptno()){
                HighestDepartmentNumber = DepartmentList.get(i).getDeptno();
            }
        }

        return HighestDepartmentNumber;
    }
    //Exercise 2
    public Department FindDepartmentWithMostEmployees(){
        ArrayList<Employee> EmployeesList = (ArrayList<Employee>) repo.getAllEntities();
        for (int i = 0; i < EmployeesList.size(); i++) {

        }
    }

}
