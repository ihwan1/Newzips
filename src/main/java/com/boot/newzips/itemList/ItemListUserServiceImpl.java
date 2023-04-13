package com.boot.newzips.itemList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boot.newzips.dto.ListingDTO;

public class ItemListUserServiceImpl implements itemListUserService {
	
	@Autowired
	private ItemListUserMapper itemListUserMapper;
	
	public List<ListingDTO> selectListingDTO() {
	    
	    List<ListingDTO> listingDTOList = new ArrayList<>(); //�� �迭 ��ü�� ����� listingDTOList�� �������
	    List<ListingDTO> listingList = itemListUserMapper.selectListingDTO(); //selectListingDTO�� ���� ��ȸ�� ������  List<ListingDTO>���·� ��ȯ�ǰ� listingList�� ����
	    for (ListingDTO listingDTO : listingList) {  //listingList�ȿ� �ִ� listingDTO�� �ݺ����� ������ convertedListingDTO�� �ִ´�
	        ListingDTO convertedListingDTO = new ListingDTO();
	     
	        // ...
	        listingDTOList.add(convertedListingDTO); //convertedListingDTO�� �Ʊ���� ��ü(listingDTOList)�� �ִ´�
	    }
	    return listingDTOList;
	}
}
