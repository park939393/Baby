package com.baby.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baby.mapper.AdminMapper;
import com.baby.mapper.AttachMapper;
import com.baby.mapper.ProductMapper;
import com.baby.model.AttachImageVO;
import com.baby.model.CateFilterDTO;
import com.baby.model.CateVO;
import com.baby.model.Criteria;
import com.baby.model.ProductVO;
import com.baby.model.SelectDTO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private AttachMapper attachMapper;

	@Autowired
	private AdminMapper adminMapper;

	/* 상품 검색 */
	@Override
	public List<ProductVO> getProductList(Criteria cri) {

		log.info("getProductList().......");

		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] brandArr = productMapper.getBrandIdList(cri.getKeyword());

		if (type.equals("B") || type.equals("BC") || type.equals("BT") || type.equals("BCT")) {
			if (brandArr.length == 0) {
				return new ArrayList();
			}
		}

		for (String t : typeArr) {
			if (t.equals("B")) {
				cri.setBrandArr(brandArr);
			}
		}

		List<ProductVO> list = productMapper.getProductList(cri);

		list.forEach(product -> {

			int productId = product.getProductId();

			List<AttachImageVO> imageList = attachMapper.getAttachList(productId);

			product.setImageList(imageList);

		});

		return list;
	}

	/* 상품 총 갯수 */
	@Override
	public int productGetTotal(Criteria cri) {

		log.info("productGetTotal().......");

		return productMapper.productGetTotal(cri);
	}

	/* 국내 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode1() {

		log.info("getCateCode1().........");

		return productMapper.getCateCode1();
	}

	/* 외국 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode2() {

		log.info("getCateCode2().........");

		return productMapper.getCateCode2();
	}

	/* 검색결과 카테고리 필터 정보 */
	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {
		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();

		String[] typeArr = cri.getType().split("");
		String[] BrandArr;

		for (String type : typeArr) {
			if (type.equals("B")) {
				BrandArr = productMapper.getBrandIdList(cri.getKeyword());
				if (BrandArr.length == 0) {
					return filterInfoList;
				}
				cri.setBrandArr(BrandArr);
			}
		}

		String[] cateList = productMapper.getCateList(cri);

		String tempCateCode = cri.getCateCode();

		for (String cateCode : cateList) {
			cri.setCateCode(cateCode);
			CateFilterDTO filterInfo = productMapper.getCateInfo(cri);
			filterInfoList.add(filterInfo);
		}

		cri.setCateCode(tempCateCode);

		return filterInfoList;
	}

	/* 상품 정보 */
	@Override
	public ProductVO getProductInfo(int productId) {

		ProductVO productInfo = productMapper.getProductInfo(productId);

		productInfo.setImageList(adminMapper.getAttachInfo(productId));

		return productInfo;
	}

	@Override
	public ProductVO getProductIdName(int productId) {

		return productMapper.getProductIdName(productId);
	}

	@Override
	public List<SelectDTO> likeSelect() {
		List<SelectDTO> list = productMapper.likeSelect();

		list.forEach(dto -> {

			int productId = dto.getProductId();

			List<AttachImageVO> imageList = attachMapper.getAttachList(productId);

			dto.setImageList(imageList);

		});

		return list;
	}

}
