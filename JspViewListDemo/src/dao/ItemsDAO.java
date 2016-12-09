package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;

import entity.Items;

//��Ʒ��ҵ���߼���
public class ItemsDAO {

	// ������е���Ʒ��Ϣ
	public ArrayList<Items> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>(); // ��Ʒ����
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items;"; // SQL���
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);// ��һ����Ʒ���뼯��
			}
			return list; // ���ؼ��ϡ�
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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

	// ������Ʒ��Ż����Ʒ����
	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	//ɾ����Ʒ
	public int deleteItemsById(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			conn = DBHelper.getConnection();
			String sql = "delete from items where id=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeUpdate();
			stmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return rs;
	}
	//�޸���Ʒ
	public int updateItemsById(int id,Items item){
		Connection conn = null;
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			conn = DBHelper.getConnection();
			String sql = "update  items  set name='"+item.getName()+"',city='"+item.getCity()+"',"
					+ "price='"+item.getPrice()+"',picture='"+item.getPicture()+"'  where id=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeUpdate();
			stmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return rs;
	}
	//�����Ʒ
	public int addItem(Items item){
		Connection conn = null;
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			conn = DBHelper.getConnection();
			String sql = "insert  items  (name,city,price,picture) values(?,?,?,?)"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, item.getName());
			stmt.setString(2, item.getCity());
			stmt.setInt(3, item.getPrice());
			stmt.setString(4, item.getPicture());
			rs = stmt.executeUpdate();
			stmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return rs;
	}	

}
