package com.boot.newzips.reservation;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.ReservInfoDTO;
import com.boot.newzips.dto.VisitorReservDTO;
import com.boot.newzips.dto.WolseListingDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationRealtorController {

	private final ReservationRealtorService reservationRealtorService;
	
	
	
	//�Ź� �湮 �����û ����Ʈ
	@GetMapping("/newzips/reservationRequestRealtor")
	public ModelAndView ReservList() throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		/*VisitorReserv�� �ٸ� ���̺��� Dto�� �־���� �� ��� ������ �ڵ�
		//������ ���� �ҷ�����
		List<VisitorReservDTO> visitorReserv = 
				reservationRealtorService.getReserverInfo();
		
		Iterator<VisitorReservDTO> it = visitorReserv.iterator();
		
		while(it.hasNext()) {
			
			VisitorReservDTO visitor = it.next();
			
			if(reservationRealtorService.getJunsaeInfo(visitor.getItemId())!=null){
				
				visitor.setJunsae(reservationRealtorService.getJunsaeInfo(visitor.getItemId()));
				
			} else if(reservationRealtorService.getWolseInfo(visitor.getItemId())!=null) {
				
				visitor.setWolse(reservationRealtorService.getWolseInfo(visitor.getItemId()));
				
			}
			
			visitor.setMember(reservationRealtorService.getMemberInfo(visitor.getUserId()));
			visitor.setListing(reservationRealtorService.getItemInfo(visitor.getItemId()));
			
		}
		
		mav.addObject("VisitorReservDTO", visitorReserv);
		*/
		
		
		//�̾ƿ� �����͸� ���ο� DTO�� �� ��Ƽ� ��°�� �ѱ� �� ����ϴ� �ڵ�
		//������ ���� �ҷ��ͼ� list�� ���
		List<ReservInfoDTO> reservInfo = reservationRealtorService.getReserverInfo(); 
		
		Iterator<ReservInfoDTO> it = reservInfo.iterator();
		
		while(it.hasNext()) {
			ReservInfoDTO dto = it.next();
			ListingDTO listingDTO  = reservationRealtorService.getItemInfo(dto.getItemId());
			
			if(listingDTO!=null) {
				
				dto.setAddrDetail(listingDTO.getAddrDetail());
				dto.setYearly_monthly(listingDTO.getYearly_monthly());
			
				if(dto.getYearly_monthly().equals("����")) {
					
					JunsaeListingDTO junsaeDTO = reservationRealtorService.getJunsaeInfo(dto.getItemId());
					dto.setYearlyFee(junsaeDTO.getYearlyFee());
				}
				else if(dto.getYearly_monthly().equals("����")) {
					WolseListingDTO wolseDTO = reservationRealtorService.getWolseInfo(dto.getItemId());
					dto.setDeposit(wolseDTO.getDeposit());
					dto.setMonthlyFee(wolseDTO.getMonthlyFee());
				}
				
			}
			
			MemberDTO memberDTO = reservationRealtorService.getMemberInfo(dto.getUserId());
			
			dto.setUserName(memberDTO.getUserName());
			dto.setUserBirth(memberDTO.getUserBirth());
			dto.setUserPhone(memberDTO.getUserPhone());
			
		}

		mav.addObject("reservInfoDTO", reservInfo);
		mav.setViewName("realtor/reservationRequestRealtor");	
		
		return mav;
	}

	//�湮 ���� ��Ȳ-�߰���
	@GetMapping("/newzips/reservationStateRealtor")
	public String reservationStateRealtor(){
		
//		ModelAndView mav = new ModelAndView();
		
	
		
		return "realtor/reservationStateRealtor";
	}

}

