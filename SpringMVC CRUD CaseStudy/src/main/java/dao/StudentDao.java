package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class StudentDao {
	JdbcTemplate jdbc;
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public int addStudent(Student s, String name, String dept){
		String sql="insert into Student(StudentName, StudentDept) values('"+name+"', '"+dept+"');";
		int rowAffected=jdbc.update(sql);
		return rowAffected;
	}
	
	public int updateStudent(Student s, int no, String name, String dept){
		String sql="update Student set StudentName='"+name+"', StudentDept='"+dept+"' where StudentNo="+no+"";
		int rowAffected=jdbc.update(sql);
		return rowAffected;
	}
	
	public List<Student> viewStudent(){
		String sql="select * from Student";
		List<Student> studentList=jdbc.query(sql, new RowMapper<Student>(){			
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s=new Student();
				s.setStudentNo(rs.getInt(1));
				s.setStudentName(rs.getString(2));
				s.setStudentDept(rs.getString(3));
				return s;
			}
		});
		return studentList;
	}
	
	public void deleteStudent(Student s, String name){
		String sql="delete from Student where StudentName='"+name+"'";
		jdbc.update(sql);
	}
}
