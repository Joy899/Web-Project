package com.xf.page.service;

import com.xf.page.dao.StudentDao;
import com.xf.page.dao.SublistStudentDaoImpl;
import com.xf.page.model.Pager;
import com.xf.page.model.Student;


/**
 * @author XF
 * ʹ��subList����ʵ�ַ�ҳ
 */
public class SublistStudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	public StudentDao getStudentDao() {
		return studentDao;
	}
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	public SublistStudentServiceImpl(){
		//������ʱ�ٳ�ʼ��Dao����
		studentDao = new SublistStudentDaoImpl();
	}
	@Override
	public Pager<Student> findStudent(Student searchMode, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		Pager<Student> result = studentDao.findStudent(searchMode, pageNum, pageSize);
		return result;
	}

}
