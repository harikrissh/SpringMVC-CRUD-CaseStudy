
package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@Configuration
//@ComponentScan(basePackages={"demo.mvc", "dao"})
@Controller
public class StudentController {
	
	@Autowired
	StudentDao sDao;
	
	@RequestMapping(value="/AddStudent", method=RequestMethod.POST)
	public String addStudentController(Student s, @RequestParam("name") String name, @RequestParam("dept") String dept){
		sDao.addStudent(s, name, dept);
		return "redirect:/ViewStudent";
	}
	
	
	@RequestMapping("/ViewStudent")
	public String viewStudentController(Model m){		
		List<Student> list=sDao.viewStudent();
		m.addAttribute("stuList", list);
		return "ViewStudent";
	}
	
	@RequestMapping(value="/UpdateStudent", method=RequestMethod.POST)
	public String updateStudentController(Student s, @RequestParam("no") int no, @RequestParam("name") String name, @RequestParam("dept") String dept){
		sDao.updateStudent(s, no, name, dept);
		return "redirect:/ViewStudent";
	}
	
	@RequestMapping("/delete/{name}")
	public String deleteStudentController(Student s, @PathVariable("name") String name) {
		sDao.deleteStudent(s, name);
		return "redirect:/ViewStudent";
	}
}
