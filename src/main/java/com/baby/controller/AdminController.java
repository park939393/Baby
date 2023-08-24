package com.baby.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baby.model.AttachImageVO;
import com.baby.model.BrandVO;
import com.baby.model.Criteria;
import com.baby.model.OrderCancelDTO;
import com.baby.model.OrderDTO;
import com.baby.model.PageDTO;
import com.baby.model.ProductVO;
import com.baby.service.AdminService;
import com.baby.service.BrandService;
import com.baby.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private BrandService brandService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderService orderService;

	// 관리자 메인 페이지 이동
	@GetMapping("main")
	public void adminMainGET() throws Exception {

		logger.info("관리자 페이지 이동");
	}

	// 상품 관리 페이지 접속
	@GetMapping("productManage")
	public void productManageGET(Criteria cri, Model model) throws Exception {

		logger.info("상품 관리 페이지 접속");

		/* 상품 리스트 데이터 */
		List list = adminService.productGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}

		/* 페이지 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, adminService.productGetTotal(cri)));

	}

	// 상품 등록 페이지 접속
	@GetMapping("productEnroll")
	public void productEnrollGET(Model model) throws Exception {

		logger.info("상품 등록 페이지 접속");

		ObjectMapper objm = new ObjectMapper();

		List list = adminService.cateList();

		String cateList = objm.writeValueAsString(list);

		model.addAttribute("cateList", cateList);

		logger.info("변경 전.........." + list);
		logger.info("변경 후.........." + cateList);

	}

	// 브랜드 등록 페이지 접속
	@GetMapping("brandEnroll")
	public void brandEnrollGET() throws Exception {
		logger.info("브랜드 등록 페이지 접속");
	}

	// 브랜드 관리 페이지 접속
	@GetMapping("brandManage")
	public void brandManageGET(Criteria cri, Model model) throws Exception {
		logger.info("브랜드 관리 페이지 접속" + cri);

		/* 브랜드 목록 출력 데이터 */
		List list = brandService.brandGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list); // 브랜드 존재 경우
		} else {
			model.addAttribute("listCheck", "empty"); // 브랜드 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		int total = brandService.brandGetTotal(cri);

		PageDTO pageMaker = new PageDTO(cri, total);

		model.addAttribute("pageMaker", pageMaker);
	}

	/* 브랜드 등록 */
	@PostMapping("brandEnroll.do")
	public String brandEnrollPOST(BrandVO brand, RedirectAttributes rttr) throws Exception {

		logger.info("brandEnroll :" + brand);

		brandService.brandEnroll(brand); // 브랜드 등록 쿼리 수행

		rttr.addFlashAttribute("enroll_result", brand.getBrandName()); // 등록 성공 메시지(브랜드 이름)

		return "redirect:/admin/brandManage";

	}

	/* 브랜드 상세 페이지 */
	@GetMapping({ "/brandDetail", "/brandModify" })
	public void brandGetInfoGET(int brandId, Criteria cri, Model model) throws Exception {

		logger.info("brandGetInfo :" + brandId);

		brandService.brandGetDetail(brandId);

		/* 브랜드 관리 페이지 정보 */
		model.addAttribute("cri", cri);

		/* 선택 브랜드 정보 */
		model.addAttribute("brandInfo", brandService.brandGetDetail(brandId));

	}

	/* 브랜드 정보 수정 */
	@PostMapping("/brandModify")
	public String brandModifyPOST(BrandVO brand, RedirectAttributes rttr) throws Exception {

		logger.info("brandModifyPOST :" + brand);

		int result = brandService.brandModify(brand);

		rttr.addFlashAttribute("modify_result", result);

		return "redirect:/admin/brandManage";

	}

	/* 상품 등록 */
	@PostMapping("/productEnroll")
	public String productEnrollPOST(ProductVO product, RedirectAttributes rttr) {

		logger.info("productEnrollPOST :" + product);

		adminService.productEnroll(product);

		rttr.addFlashAttribute("enroll_result", product.getProductName());

		return "redirect:/admin/productManage";

	}

	/* 브랜드 검색 팝업창 */
	@GetMapping("/brandPop")
	public void brandPopGET(Criteria cri, Model model) throws Exception {

		logger.info("brandPopGET.....");

		cri.setAmount(5);

		/* 게시물 출력 데이터 */
		List list = brandService.brandGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list); // 브랜드 존재 경우
		} else {
			model.addAttribute("listCheck", "empty"); // 브랜드 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, brandService.brandGetTotal(cri)));

		logger.info("brandPopGET.....");

	}

	/* 상품 조회 페이지 */
	@GetMapping({ "/productDetail", "/productModify" })
	public void productGetInfoGET(int productId, Criteria cri, Model model) throws JsonProcessingException {

		logger.info("productDetail() :" + productId);

		ObjectMapper mapper = new ObjectMapper();

		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));

		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);

		/* 조회 페이지 정보 */
		model.addAttribute("productInfo", adminService.productGetDetail(productId));

	}

	/* 상품 정보 수정 */
	@PostMapping("/productModify")
	public String productModifyPOST(ProductVO vo, RedirectAttributes rttr) {

		logger.info("productModifyPOST.........." + vo);

		int result = adminService.productModify(vo);

		rttr.addFlashAttribute("modify_result", result);

		return "redirect:/admin/productManage";

	}

	/* 상품 정보 삭제 */
	@PostMapping("/productDelete")
	public String productDeletePOST(int productId, RedirectAttributes rttr) {

		logger.info("productDeletePOST..........");

		List<AttachImageVO> fileList = adminService.getAttachInfo(productId);

		if (fileList != null) {
			List<Path> pathList = new ArrayList();

			fileList.forEach(vo -> {

				// 원본 이미지
				Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);

				// 섬네일 이미지
				path = Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName());
				pathList.add(path);

			});

			pathList.forEach(path -> {
				path.toFile().delete();
			});
		}

		int result = adminService.productDelete(productId);

		rttr.addFlashAttribute("delete_result", result);

		return "redirect:/admin/productManage";
	}

	/* 브랜드 정보 삭제 */
	@PostMapping("/brandDelete")
	public String brandDeletePOST(int brandId, RedirectAttributes rttr) {

		logger.info("brandDeletePOST..........");

		int result = 0;

		try {

			result = adminService.brandDelete(brandId);

		} catch (Exception e) {

			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);

			return "redirect:/admin/brandManage";

		}

		rttr.addFlashAttribute("delete_result", result);

		return "redirect:/admin/brandManage";

	}

	/* 첨부 파일 업로드 */
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {

		logger.info("uploadAjaxActionPOST....");

		/* 이미지 파일 체크 */
		for (MultipartFile multipartFile : uploadFile) {

			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;

			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!type.startsWith("image")) {

				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}
		} // for

		String uploadFolder = "C:\\upload";

		/* 날짜 폴더 경로 */

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		String datePath = str.replace("-", File.separator);

		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();

		for (MultipartFile multipartFile : uploadFile) {

			/* 이미지 정보 객체 */
			AttachImageVO vo = new AttachImageVO();

			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);

			/* uuid 적용 파일 이름 */
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);

			uploadFileName = uuid + "_" + uploadFileName;

			/* 파일 위치, 파일 이름을 합친 file 객체 */
			File saveFile = new File(uploadPath, uploadFileName);

			/* 파일 저장 */
			try {
				multipartFile.transferTo(saveFile);

				/* 썸네일 생성 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);

				BufferedImage bo_image = ImageIO.read(saveFile);
				/* 비율 */
				double ratio = 3;
				/* 넓이 높이 */
				int width = (int) (bo_image.getWidth() / ratio);
				int height = (int) (bo_image.getHeight() / ratio);

				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

				Graphics2D graphic = bt_image.createGraphics();

				graphic.drawImage(bo_image, 0, 0, width, height, null);

				ImageIO.write(bt_image, "jpg", thumbnailFile);

			} catch (Exception e) {
				e.printStackTrace();
			}

			list.add(vo);

		} // for
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);

		return result;
	}

	/* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) {

		logger.info("deleteFile..." + fileName);

		File file = null;

		try {

			/* 썸네일 파일 삭제 */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replaceFirst("s_", "");

			logger.info("originFileName : " + originFileName);

			file = new File(originFileName);

			file.delete();

		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);

		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	// 이미지 업로드
		@RequestMapping(value = "/imageUpload.do", method = RequestMethod.POST)
		public void imageUpload(HttpServletRequest request, HttpServletResponse response,
				MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws Exception {
			// 랜덤 문자 생성
			UUID uid = UUID.randomUUID();

			OutputStream out = null;
			PrintWriter printWriter = null;

			// 인코딩
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			try {
				// 파일 이름 가져오기
				String fileName = upload.getOriginalFilename();
				byte[] bytes = upload.getBytes();

				// 이미지 경로 생성
				String path = "C:\\upload\\Pictures\\Saved Pictures" + "ckImage/"; // 이미지 경로 설정(폴더 자동 생성)
				String ckUploadPath = path + uid + "_" + fileName;
				File folder = new File(path);
				System.out.println("path:" + path); // 이미지 저장경로 console에 확인
				// 해당 디렉토리 확인
				if (!folder.exists()) {
					try {
						folder.mkdirs(); // 폴더 생성
					} catch (Exception e) {
						e.getStackTrace();
					}
				}

				out = new FileOutputStream(new File(ckUploadPath));
				out.write(bytes);
				out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화

				String callback = request.getParameter("CKEditorFuncNum");
				printWriter = response.getWriter();
				String fileUrl = "/admin/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName; // 작성화면

				// 업로드시 메시지 출력
				printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
				printWriter.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (printWriter != null) {
						printWriter.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		}

		// 서버로 전송된 이미지 뿌려주기
		@RequestMapping(value = "/ckImgSubmit.do")
		public void ckSubmit(@RequestParam(value = "uid") String uid, @RequestParam(value = "fileName") String fileName,
				HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			// 서버에 저장된 이미지 경로
			String path = "C:\\upload\\Pictures\\Saved Pictures" + "ckImage/"; // 저장된 이미지 경로
			System.out.println("path:" + path);
			String sDirPath = path + uid + "_" + fileName;

			File imgFile = new File(sDirPath);

			// 사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
			if (imgFile.isFile()) {
				byte[] buf = new byte[1024];
				int readByte = 0;
				int length = 0;
				byte[] imgBuf = null;

				FileInputStream fileInputStream = null;
				ByteArrayOutputStream outputStream = null;
				ServletOutputStream out = null;

				try {
					fileInputStream = new FileInputStream(imgFile);
					outputStream = new ByteArrayOutputStream();
					out = response.getOutputStream();

					while ((readByte = fileInputStream.read(buf)) != -1) {
						outputStream.write(buf, 0, readByte);
					}

					imgBuf = outputStream.toByteArray();
					length = imgBuf.length;
					out.write(imgBuf, 0, length);
					out.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					outputStream.close();
					fileInputStream.close();
					out.close();
				}
			}
		}

	/* 주문 현황 페이지 */
	@GetMapping("/orderList")
	public String orderListGET(Criteria cri, Model model) {
		List<OrderDTO> list = adminService.getOrderList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list);
			model.addAttribute("pageMaker", new PageDTO(cri, adminService.getOrderTotal(cri)));
		} else {
			model.addAttribute("listCheck", "empty");
		}

		return "/admin/orderList";
	}
	
	/* 주문삭제 */
	@PostMapping("/orderCancle")
	public String orderCanclePOST(OrderCancelDTO dto) {
		
		orderService.orderCancle(dto);
		
		return "redirect:/admin/orderList?keyword=" + dto.getKeyword() + "&amount=" + dto.getAmount() + "&pageNum=" + dto.getPageNum();
	}

}
