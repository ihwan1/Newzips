package com.boot.newzips.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.boot.newzips.dto.VisitorReservDTO;

@Mapper
public interface ReservationUserMapper {
	
		// �湮�� ����
		public void insertReservation(VisitorReservDTO dto) throws Exception;
		
		// ���� ������ ��¥ ��ȸ
		public String selectAvailableDate(Map<String, Object> map) throws Exception;
		
		// ���� ������ �ð� ��ȸ
		public String selectAvailableTime(Map<String, Object> map) throws Exception;
		
		// �����ȣ�� ��ȸ
		public VisitorReservDTO selectReservationReservNo(int reservNo) throws Exception;
		
		// �������̵�� ��ȸ
		public VisitorReservDTO selectReservationUserId(String userId) throws Exception;
		
		// ����
		public void deleteReservation(int reservNo) throws Exception;
}
