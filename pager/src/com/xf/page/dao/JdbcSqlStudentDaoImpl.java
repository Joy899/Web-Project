package com.xf.page.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xf.page.model.Pager;
import com.xf.page.model.Student;
import com.xf.page.util.JdbcUtil;

/**
 * @author XF
 * ʹ��MySql���ݿ�limitʵ�ַ�ҳ
 */
public class JdbcSqlStudentDaoImpl implements StudentDao {

	@Override
	public Pager<Student> findStudent(Student searchModel, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		Pager<Student> result = null;
		List<Object> paramList = new ArrayList<Object>();
		
		String stuName = searchModel.getName();
//		System.out.println("impl : name: "+stuName);
		int gender = searchModel.getGender();
		
		StringBuilder sql = new StringBuilder("select * from t_student where 1=1");
		//��Ϊʹ����limit���޷���ȡ������������ʹ����һ��sql�������ѯ������
		StringBuilder countSql = new StringBuilder("select count(id) as totalRecord from t_student where 1=1 ");
		
		if(stuName != null && !stuName.equals("")){
			sql.append(" and stu_name like ?");
			countSql.append(" and stu_name like ?");
			paramList.add("%"+stuName+"%");
		}
		if(gender == Constant.GENDER_FEMALE || gender == Constant.GENDER_MALE){
			sql.append(" and gender = ?");
			countSql.append(" and gender = ?");
			paramList.add(gender);
		}

		
		List<Student> studentList = new ArrayList<Student>(); 
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection();
			//��ȡ������
			List<Map<String, Object>> countResult = jdbcUtil.findResult(countSql.toString(), paramList);
			Map<String, Object> countMap =  countResult.get(0);
			int totalRecord = ((Number)countMap.get("totalRecord")).intValue();
			//������ҳ��
			int totalPage = totalRecord / pageSize;
			//������+1
			if(totalRecord % pageSize != 0) totalPage +=1;
			//��pageNum�����ں���Χ
			if(pageNum > totalPage) pageNum = totalPage;
			if(pageNum<1) pageNum = 1;
			int fromIndex = pageSize*(pageNum -1 );
			sql.append(" limit "+fromIndex+","+pageSize);
			//��ȡ��ѯ��ѧ����¼
			List<Map<String, Object>> studentResult = jdbcUtil.findResult(sql.toString(), paramList);
			if(studentResult != null){
				for(Map<String, Object> map:studentResult){
					Student s = new Student(map);
					studentList.add(s);
				}
			}

			
			result = new Pager<Student>(pageSize, pageNum, totalRecord, totalPage, studentList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("��ѯ���������쳣!",e);
		}finally{
			if(jdbcUtil != null){
				jdbcUtil.releaseConn();
			}
		}
		return result;
	}

}
