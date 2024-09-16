package in.subha.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.subha.entity.Course;
import in.subha.repo.CourseRepo;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportService {
	
	@Autowired
	private CourseRepo repo;
	
	public String saveCourse(Course c) {
		Course course = repo.save(c);
		if(course.getId()!=null)
			return "Course details saved...";
		else
			return "Failed to save";
	}
		
	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Course> courses = repo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Courses Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Fees");
		row.createCell(3).setCellValue("Duration");

		int dataRowIndex = 1;

		for (Course course : courses) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(course.getId());
			dataRow.createCell(1).setCellValue(course.getName());
			dataRow.createCell(2).setCellValue(course.getFees());
			dataRow.createCell(3).setCellValue(course.getDuration());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

}





