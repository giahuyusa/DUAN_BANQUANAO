package com.poly.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poly.model.product;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
	@Autowired
   
    private List<product> getProducts() {
        return List.of(
        		new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-polo-mlb-chinh-hang-mono-logo-ny-mau-ivory-3apqm0443-50ivs-2.jpg?v=1714634639083", "Áo Polo MLB Korea Classic Mono", 3190000.0),
                new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-thun-mlb-chinh-hang-basic-logo-c-mau-be-3atsb1143-45bgl-2.jpg?v=1710262555370", "Áo Thun MLB Basic Cổ Tròn Manogram", 2990000.0),
                new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-thun-mlb-monogram-all-over-print-boston-red-sox-black-3atsm4023-43bks-2.jpg?v=1654410162017", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-collar-tie-logo-la-mau-xanh-duong-3akpv0543-07bls-1.jpg?v=1713547927737", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-polo-mlb-chinh-hang-logo-ny-mau-navy-3apqv0143-50nys-1.jpg?v=1714635649210", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-ny-mau-den-3ftsv1243-50bks-1.jpg?v=1714586311843", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-b-mau-do-3ftsv0343-43rds-1.jpg?v=1713686221073", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-ny-mau-den-3ftsv1243-50bks-1.jpg?v=1714586311843", "Áo Polo MLB Monogram", 3290000.0),
                
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-polo-mlb-chinh-hang-logo-ny-mau-navy-3apqv0143-50nys-1.jpg?v=1714635649210", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-ny-mau-den-3ftsv1243-50bks-1.jpg?v=1714586311843", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-b-mau-do-3ftsv0343-43rds-1.jpg?v=1713686221073", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-ny-mau-den-3ftsv1243-50bks-1.jpg?v=1714586311843", "Áo Polo MLB Monogram", 3290000.0),
            	new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-polo-mlb-chinh-hang-mono-logo-ny-mau-ivory-3apqm0443-50ivs-2.jpg?v=1714634639083", "Áo Polo MLB Korea Classic Mono", 3190000.0),
                new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-thun-mlb-chinh-hang-basic-logo-c-mau-be-3atsb1143-45bgl-2.jpg?v=1710262555370", "Áo Thun MLB Basic", 2990000.0),
                new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-thun-mlb-monogram-all-over-print-boston-red-sox-black-3atsm4023-43bks-2.jpg?v=1654410162017", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-collar-tie-logo-la-mau-xanh-duong-3akpv0543-07bls-1.jpg?v=1713547927737", "Áo Polo MLB Monogram", 3290000.0),
               
                new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-thun-mlb-monogram-all-over-print-boston-red-sox-black-3atsm4023-43bks-2.jpg?v=1654410162017", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-collar-tie-logo-la-mau-xanh-duong-3akpv0543-07bls-1.jpg?v=1713547927737", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-polo-mlb-chinh-hang-logo-ny-mau-navy-3apqv0143-50nys-1.jpg?v=1714635649210", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-ny-mau-den-3ftsv1243-50bks-1.jpg?v=1714586311843", "Áo Polo MLB Monogram", 3290000.0),
            	new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-polo-mlb-chinh-hang-mono-logo-ny-mau-ivory-3apqm0443-50ivs-2.jpg?v=1714634639083", "Áo Polo MLB Korea Classic Mono", 3190000.0),
                new product("https://bizweb.dktcdn.net/thumb/large/100/446/974/products/ao-thun-mlb-chinh-hang-basic-logo-c-mau-be-3atsb1143-45bgl-2.jpg?v=1710262555370", "Áo Thun MLB Basic", 2990000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-b-mau-do-3ftsv0343-43rds-1.jpg?v=1713686221073", "Áo Polo MLB Monogram", 3290000.0),
                new product("https://bizweb.dktcdn.net/100/446/974/products/ao-croptop-mlb-chinh-hang-logo-ny-mau-den-3ftsv1243-50bks-1.jpg?v=1714586311843", "Áo Polo MLB Monogram", 3290000.0)
            );
    }

    @GetMapping("/home/index")
    public String getProductList(Model model) {
        List<product> products = getProducts(); 

        List<product> bestSeller = products.stream().limit(8).collect(Collectors.toList());
        List<product> newArrivals = products.stream().skip(8).limit(8).collect(Collectors.toList());
        List<product> hotSales = products.stream().skip(16).limit(8).collect(Collectors.toList());

        model.addAttribute("bestSeller", bestSeller);
        model.addAttribute("newArrivals", newArrivals);
        model.addAttribute("hotSales", hotSales);

        return "product-list";
    }


 
  
    @GetMapping("/sanpham/accounts")
    public String showadmin(Model model) {
    return "quantri";
    }
    
   
}
