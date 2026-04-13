package org.cloud.service;

import java.io.File;
import java.util.List;
import org.cloud.dto.ProductDTO;
import org.cloud.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<ProductDTO> productList() throws Exception {
		return productMapper.productList();
	}
	
	@Override
	public int insertProduct(ProductDTO product) throws Exception {
		handleFile(product); // 기존 파일 저장 로직 실행
		return productMapper.insertProduct(product);
	}
	
	@Override
	public ProductDTO productDetail(int num) throws Exception {
		return productMapper.productDetail(num);
	}
	
	@Override
	public int updateProduct(ProductDTO product) throws Exception {
		handleFile(product); // 수정 시에도 파일이 있으면 처리
		return productMapper.updateProduct(product);
	}
	
	@Override
	public int deleteProduct(int num) throws Exception {
		return productMapper.deleteProduct(num);
	}

	// 기존 코드를 기반으로 한 파일 처리 전용 메서드
	private void handleFile(ProductDTO product) throws Exception {
		MultipartFile file = product.getFile();
		if (file != null && !file.isEmpty()) {
			String projectPath = System.getProperty("user.dir"); 
			String savePath = projectPath + "/src/main/resources/static/uploads/";
			
			File folder = new File(savePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			file.transferTo(new File(savePath + fileName));
			product.setStoredFilePath("/uploads/" + fileName); // 기존 DTO 필드명 유지
		}
	}
}



