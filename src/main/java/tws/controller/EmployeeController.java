package tws.controller;

import java.util.List;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tws.entity.Employee;
import tws.mapper.EmployeeMapper;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeMapper employeeMapper;

	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam(required = false) Integer pageNum,
									   @RequestParam(required = false) Integer pageSize)
	{
		if (pageNum == null && pageSize == null){
			return ResponseEntity.ok(employeeMapper.findAllEmployees());
		}else if(pageNum != null && pageSize != null){
			PageHelper.startPage(pageNum, pageSize);
			return ResponseEntity.ok(employeeMapper.findAllEmployees());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee (@RequestBody Employee employee){
		 employeeMapper.insertOne(employee);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		employeeMapper.updateOne(id, employee);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteEmployee(@PathVariable int id) {
		employeeMapper.deleteOne(id);
	}


}
