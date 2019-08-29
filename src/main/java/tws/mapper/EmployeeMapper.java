package tws.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tws.entity.Employee;

@Mapper
public interface EmployeeMapper {
	
	@Select("select * from employee;")
	public List<Employee> findAllEmployees();
	
	@Insert("insert into employee values (#{employee.id}, #{employee.name}, #{employee.age});")
	public void insertOne(@Param("employee") Employee employee);
	
	@Update("update employee set employee.name=#{employee.name}, employee.age=#{employee.age} where employee.id = #{id};")
	public void updateOne(@Param("id") int id, @Param("employee") Employee employee);
	
	@Delete("delete from employee where employee.id = #{id};")
	public void deleteOne(@Param("id") int id);

}
