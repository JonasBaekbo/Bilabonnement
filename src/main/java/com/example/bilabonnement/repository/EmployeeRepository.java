package com.example.bilabonnement.repository;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IRepository<Employee>{

    @Override
    public List<Employee> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Employee temp = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
                allEmployees.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allEmployees;
    }

    @Override
    public Employee getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            rs.next();
            Employee temp = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8));
            return temp;
        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(Employee entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("insert into employees (id,employee_name,job,manager,hiredate,salary,commission,department_number) values (?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getEmp_id());
            pstmt.setString(2, entity.getEmp_name());
            pstmt.setString(3, entity.getEmp_job());
            pstmt.setInt(4, entity.getEmp_manager());
            pstmt.setString(5, entity.getEmp_hiredate());
            pstmt.setInt(6, entity.getEmp_salary());
            pstmt.setInt(7, entity.getEmp_commission());
            pstmt.setInt(8, entity.getEmp_departmentno());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Employee> getSingleByDepartmentno(int depno){
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees WHERE department_no = ?");
            pstmt.setInt(1, depno);
                    pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            while(rs.next()){
                Employee temp = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
                allEmployees.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allEmployees;
    }

}
