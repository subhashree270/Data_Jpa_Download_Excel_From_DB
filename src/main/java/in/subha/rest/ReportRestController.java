package in.subha.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.subha.entity.Course;
import in.subha.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {
	@Autowired
	private ReportService service;
	
	@PostMapping("/course")
	public ResponseEntity<String> saveCourse(@RequestBody Course c) {
		String savedCourse = service.saveCourse(c);
		return new ResponseEntity<>(savedCourse,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=courses.xls";

		response.setHeader(headerKey, headerValue);
		
		service.generateExcel(response);
		
		response.flushBuffer();
	}
}