package com.baby.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baby.model.AttachImageVO;
import com.baby.model.Criteria;
import com.baby.model.PageDTO;
import com.baby.model.ProductVO;
import com.baby.model.ReviewDTO;
import com.baby.service.AttachService;
import com.baby.service.ProductService;
import com.baby.service.ReviewService;

@Controller
public class BabyController {

	private static final Logger logger = LoggerFactory.getLogger(BabyController.class);

	@Autowired
	private AttachService attachService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPageGET(Model model) {
		logger.info("메인 페이지 진입");

		model.addAttribute("cate1", productService.getCateCode1());
		model.addAttribute("cate2", productService.getCateCode2());
		model.addAttribute("ls", productService.likeSelect());

	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName) {

		logger.info("getImage()..." + fileName);

		File file = new File("c:\\upload\\" + fileName);

		ResponseEntity<byte[]> result = null;

		try {

			HttpHeaders header = new HttpHeaders();

			header.add("Content-type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* 이미지 정보 반환 */
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int productId) {

		logger.info("getAttachList.........." + productId);

		return new ResponseEntity(attachService.getAttachList(productId), HttpStatus.OK);
	}

	/* 상품 검색 */

	@GetMapping("/search")
	public String searchProductGET(Criteria cri, Model model) {

		logger.info("cri : " + cri);

		List<ProductVO> list = productService.getProductList(cri);
		logger.info("pre list : " + list);
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");

			return "search";
		}

		model.addAttribute("pageMaker", new PageDTO(cri, productService.productGetTotal(cri)));

		String[] typeArr = cri.getType().split("");

		for (String s : typeArr) {
			if (s.equals("T") || s.equals("B")) {
			model.addAttribute("filter_info", productService.getCateInfoList(cri));
			}
		}

		return "search";

	}
	
	/* 상품 상세 */
	@GetMapping("/productDetail/{productId}")
	public String productDetailGET(@PathVariable("productId")int productId, Model model) {
		
		logger.info("productDetailGET()..........");
		
		model.addAttribute("productInfo", productService.getProductInfo(productId));
		
		return "/productDetail";
	}
	
	/* 리뷰 쓰기 */
	@GetMapping("/reviewEnroll/{memberId}")
	public String reviewEnrollWindowGET(@PathVariable("memberId")String memberId, int productId, Model model) {
		ProductVO product = productService.getProductIdName(productId);
		model.addAttribute("productInfo", product);
		model.addAttribute("memberId", memberId);
		
		return "/reviewEnroll";
	}
	
	/* 리뷰 수정 팝업창 */
	@GetMapping("/reviewUpdate")
	public String reviewUpdateWindowGET(ReviewDTO dto, Model model) {
		ProductVO product = productService.getProductIdName(dto.getProductId());
		model.addAttribute("productInfo", product);
		model.addAttribute("reviewInfo", reviewService.getUpdateReview(dto.getReviewId()));
		model.addAttribute("memberId", dto.getMemberId());
		
		return "/reviewUpdate";
	}
	
	

}
