package org.cloud.controll;

import java.util.List;
import org.cloud.dto.ProductDTO;
import org.cloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

	@Autowired	
	private ProductService productService;
	
	@GetMapping("/list")
	public List<ProductDTO> openProductList() throws Exception {
		return productService.productList();
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertProduct(@ModelAttribute ProductDTO product) throws Exception {
		productService.insertProduct(product);
		return ResponseEntity.ok("insert success");
	}
	
	@GetMapping("/detail/{num}")
	public ProductDTO openProductDetail(@PathVariable("num") int num) throws Exception{
		return productService.productDetail(num);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@ModelAttribute ProductDTO product) throws Exception {
		productService.updateProduct(product);
		return ResponseEntity.ok("update success");
	}
	
	@DeleteMapping("/delete/{num}") // {num} 경로 변수 추가
	public ResponseEntity<String> deleteProduct(@PathVariable("num") int num) throws Exception {
		productService.deleteProduct(num);
		return ResponseEntity.ok("delete success");
	}
}