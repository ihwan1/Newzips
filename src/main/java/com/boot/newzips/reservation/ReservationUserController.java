package com.boot.newzips.reservation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;

import com.boot.newzips.service.ReservationUserService;
import com.boot.newzips.service.ResidentService;

@RestController
public class ReservationUserController {
	
	@Resource //������ ������ �����ν� Service �ȿ� �ִ� ResidentServiceImpl�� ������
	private ResidentService residentService; 
	
	@Resource
	private ReservationUserService reservationUserService;
	
	
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
	}

	
	
	
	@GetMapping("/newzips/reservation_user1.action")
	public ModelAndView reservation_user(HttpServletRequest request) throws Exception{
		
		//�Ź���ȣ�� �ּҿ��� �޾ƿ���
		//���Ƿ� ����
		String itemId = request.getParameter("itemId");
		itemId = "32906223";
		
		
		//�Ź���ȣ �������� ������ �ҷ�����
		VisitorReservDTO dtoV = reservationUserService.selectReservationItemId(itemId);
		RoomInfoDTO dtoR = reservationUserService.getReservationRoomInfo(itemId);
		
		ModelAndView mav = new ModelAndView();
		
		//mav�� ������ ���
		mav.addObject("dtoV",dtoV);
		mav.addObject("dtoR",dtoR);
		
		mav.setViewName("user/reservation_user1");
		
		return mav;
		
	}
	
	
	@PostMapping("/newzips/reservation_user1.action")
	public ModelAndView reservation_user_ok(VisitorReservDTO dto,HttpServletRequest request) throws Exception{
		
		System.out.println("post ���!!");
		//reservation_user�޼ҵ�� �����ϰ� �ۼ��ϴµ�,
		//reservation_user1���������� �Ѿ�� ��¥�� �ð��� ������
		//���� ������ �����ͺ��̽��� �ִ°����� �߰��ؼ� �ۼ�!!
		
		ModelAndView mav = new ModelAndView();
		
				
		mav.setViewName("redirect:/reservation_user_complete1.action");
		
		return mav;
	}
	
	@GetMapping("/newzips/reservation_user_complete1.action")
	public ModelAndView reservation_user_complete() throws Exception{
		
		//�ּҿ��� itemId �޾ƿ���
		//�ش� id�� ���� ������ �ҷ�����
		
		//mav�� ���
		
				
		ModelAndView mav = new ModelAndView();
		
		//
		
		mav.setViewName("user/reservation_user_complete1");
		
		return mav;
	}
		
	

}
