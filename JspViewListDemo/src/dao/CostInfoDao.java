package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBHelper;
import entity.VipUser;

public class CostInfoDao {

	//������Ʒ��Ϣ
	public void addCostItem(int id , String uname){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from vipuser where uname=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uname);
			rs = stmt.executeQuery();
			String ugoods = "";
			while(rs.next()){
				ugoods = rs.getString("ugoods");
			}
			stmt.close();
			ugoods = ugoods+String.valueOf(id)+";";
			String sql1 = "update  vipuser  set ugoods=? where uname=?;";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, ugoods);
			stmt.setString(2, uname);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
	//ɾ����Ʒ��Ϣ
	public void deleteCostItem(int id , String uname){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from vipuser where uname=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uname);
			rs = stmt.executeQuery();
			String ugoods = "";
			while(rs.next()){
				ugoods = rs.getString("ugoods");
			}
			String temp="";
			String[] arrStr = ugoods.split(";");
			for(int i=0; i<arrStr.length; i++){
				String dId = String.valueOf(id);
				if(!arrStr[i].equals(dId)){
					temp += arrStr[i]+";";
				}
			}
			if(arrStr.length ==1){
				temp = "";
			}
			ugoods = temp;
			stmt.close();
			String sql1 = "update  vipuser  set ugoods=? where uname=?;";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, ugoods);
			stmt.setString(2, uname);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
}
