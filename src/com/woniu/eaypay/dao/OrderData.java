package com.woniu.eaypay.dao;

public class OrderData {
	
	//����״̬����
	public static final int orders=1; //�µ�
	public static final int pass=2;  //���ͨ��
	public static final int Picking=3; //���
	public static final int deliver=4; //�ͻ���
	public static final int take=5; //�ջ���ȷ��
	
	//����֧������
	public static final int cashOnDelivery=1;//��������
	public static final int onlinePay=2;//����֧��
	

	private int eo_id;              //����ID
	private int eo_user_id;         //�û�ID
	private String eo_user_name;    //�û�����
	private String eo_user_address; //�û���ַ
	private String eo_create_time;  //��������ʱ��
	private float eo_cost;          //�������
	private int eo_status;          //����״̬
	private int eo_type;            //�������ʽ
	
	public int getEo_id() {
		return eo_id;
	}
	public void setEo_id(int eo_id) {
		this.eo_id = eo_id;
	}
	public int getEo_user_id() {
		return eo_user_id;
	}
	public void setEo_user_id(int eo_user_id) {
		this.eo_user_id = eo_user_id;
	}
	public String getEo_user_name() {
		return eo_user_name;
	}
	public void setEo_user_name(String eo_user_name) {
		this.eo_user_name = eo_user_name;
	}
	public String getEo_user_address() {
		return eo_user_address;
	}
	public void setEo_user_address(String eo_user_address) {
		this.eo_user_address = eo_user_address;
	}
	public String getEo_create_time() {
		return eo_create_time;
	}
	public void setEo_create_time(String eo_create_time) {
		this.eo_create_time = eo_create_time;
	}
	public float getEo_cost() {
		return eo_cost;
	}
	public void setEo_cost(float eo_cost) {
		this.eo_cost = eo_cost;
	}
	public int getEo_status() {
		return eo_status;
	}
	public void setEo_status(int eo_status) {
		this.eo_status = eo_status;
	}
	public int getEo_type() {
		return eo_type;
	}
	public void setEo_type(int eo_type) {
		this.eo_type = eo_type;
	}
	public static int getOrders() {
		return orders;
	}
	public static int getPass() {
		return pass;
	}
	public static int getPicking() {
		return Picking;
	}
	public static int getDeliver() {
		return deliver;
	}
	public static int getTake() {
		return take;
	}
	public static int getCashondelivery() {
		return cashOnDelivery;
	}
	public static int getOnlinepay() {
		return onlinePay;
	}
	@Override
	public String toString() {
		return "OrderData [eo_id=" + eo_id + ", eo_user_id=" + eo_user_id
				+ ", eo_user_name=" + eo_user_name + ", eo_user_address="
				+ eo_user_address + ", eo_create_time=" + eo_create_time
				+ ", eo_cost=" + eo_cost + ", eo_status=" + eo_status
				+ ", eo_type=" + eo_type + "]";
	}
	        
}
