package com.boot.newzips.suggest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListingDTO;
import com.boot.newzips.dto.RealtorListDTO;
import com.boot.newzips.dto.RealtorSuggestionDTO;
import com.boot.newzips.dto.RoomInfoDTO;
import com.boot.newzips.dto.SuggestionDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class SuggestRealtorController {

	private final SuggestRealtorService suggestRealtorService;
	ModelAndView mav = new ModelAndView();
	
	@GetMapping("/newzips/clientListRealtor")
	public ModelAndView clientList() throws Exception{
		
		List<SuggestionDTO> suggestion = suggestRealtorService.getSuggestList();
		
		mav.addObject("suggestionDTO", suggestion);
		mav.setViewName("realtor/clientListRealtor");

		return mav;
		
		}
		
	@GetMapping("/newzips/suggestListRealtor")
	public ModelAndView suggestList(HttpServletRequest req) throws Exception{
		
		//������ ������ ������� �ּ� ����
		String userId = req.getParameter("userId");
		String addrCity = req.getParameter("addrCity");
		String addrGu = req.getParameter("addrGu");
		
		//���� �����ϰ� �Ź�����Ʈ�� �ߴ� ������
		SuggestionDTO suggest = suggestRealtorService.getSuggestInfo(userId);
		List<ListingDTO> listing = suggestRealtorService.getSuggestItem(suggest.getAddrCity(), suggest.getAddrGu());
		List<RealtorListDTO> realtorList = new ArrayList<RealtorListDTO>();
		
		Iterator<ListingDTO> it = listing.iterator();
		
		while(it.hasNext()) {

			ListingDTO dto=it.next();
			RealtorListDTO realtor = new RealtorListDTO();

			//����ڰ� ������ �Ź� ������ list�� �ִ� �Ź��� ����,��/������ ������
			if(dto.getAddrCity().equals(suggest.getAddrCity())&&dto.getAddrGu().equals(suggest.getAddrGu())
					&&dto.getYearly_monthly().equals(suggest.getYearly_monthly())) {
				//�ݾװ� roomtype ��
				//������ ��ġ�ϸ� realtorSuggestion�� userId�� itemId ���
					if(suggest.getYearly_monthly().equals("����")) {
						
						int junsaeDeposit = (suggestRealtorService.getJunsaeInfo(dto.getItemId())).getYearlyFee();
						
						if(suggest.getDeposit()>=junsaeDeposit) {
							if(suggest.getRooms()==(suggestRealtorService.getRoomInfo(dto.getItemId())).getRooms()) {

								JunsaeListingDTO junsae = suggestRealtorService.getJunsaeInfo(dto.getItemId());
								RoomInfoDTO room = suggestRealtorService.getRoomInfo(dto.getItemId());
								
								realtor.setItemId(dto.getItemId());
								realtor.setAddrCity(dto.getAddrCity());
								realtor.setAddrGu(dto.getAddrGu());
								realtor.setYearly_monthly(dto.getYearly_monthly());
								realtor.setDeposit(junsae.getYearlyFee_web());
								realtor.setMaintenanceCost(dto.getMaintenanceCost());
								realtor.setArea(room.getArea());
								realtor.setRoomType(room.getRoomType());
								realtor.setRooms(room.getRooms());
						}
						
					} else if(suggest.getYearly_monthly().equals("����")) {

						int monthlyFee = (suggestRealtorService.getWolseInfo(dto.getItemId())).getMonthlyFee();
						
						if(suggest.getMonthlyFee()>=monthlyFee) {
							if(suggest.getRooms()==(suggestRealtorService.getRoomInfo(dto.getItemId())).getRooms()) {
								
								WolseListingDTO wolse = suggestRealtorService.getWolseInfo(dto.getItemId());
								RoomInfoDTO room = suggestRealtorService.getRoomInfo(dto.getItemId());
								
								realtor.setItemId(dto.getItemId());
								realtor.setAddrCity(dto.getAddrCity());
								realtor.setAddrGu(dto.getAddrGu());
								realtor.setYearly_monthly(dto.getYearly_monthly());
								realtor.setMonthlyFee(wolse.getMonthlyFee_web());
								realtor.setMaintenanceCost(dto.getMaintenanceCost());
								realtor.setArea(room.getArea());
								realtor.setRoomType(room.getRoomType());
								realtor.setRooms(room.getRooms());
						}
						
					}
				}
			}
					
					realtorList.add(realtor);
		}
			
		}
		
		mav.addObject("suggest", suggest);
		mav.addObject("realtorListDTO", realtorList);
		mav.setViewName("realtor/suggestListRealtor");
		
		return mav;
		
	}
	

	
}



