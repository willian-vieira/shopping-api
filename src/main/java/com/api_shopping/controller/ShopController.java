package com.api_shopping.controller;

import com.api_shopping.dto.ShopDTO;
import com.api_shopping.dto.ShopReportDTO;
import com.api_shopping.service.ProductService;
import com.api_shopping.service.ShopService;
import com.api_shopping.service.UserService;
import dtos.ProductDTO;
import dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String get() {
        return "API Running! 🚀";
    }

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        List<ShopDTO> products = this.shopService.getAll();
        return products;
    }

    @GetMapping("/shoppping/userByCPF/{cpf}")
    public UserDTO getUserByCpf(@PathVariable String cpf) throws IOException, InterruptedException {
        UserDTO userDTO = this.userService.getUserByCpf(cpf);
        return userDTO;
    }

    @GetMapping("/shoppping/productByIdentifier/{productIdentifier}")
    public ProductDTO getProductByIdentifier(@PathVariable String productIdentifier) {
        ProductDTO productDTO = this.productService.getProductByIdentifier(productIdentifier);
        return productDTO;
    }

    @GetMapping("/shoppping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        List<ShopDTO> products = this.shopService.getByUser(userIdentifier);
        return products;
    }

    @GetMapping("/shoppping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        List<ShopDTO> products = this.shopService.getByDate(shopDTO);
        return products;
    }

    @GetMapping("/shoppping/{id}")
    public ShopDTO getShops(@PathVariable Long id) {
        ShopDTO products = this.shopService.findById(id);
        return products;
    }

    @PostMapping("/shoppping")
    public ShopDTO newShop(@Validated @RequestBody ShopDTO shopDTO) {
        return this.shopService.save(shopDTO);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsFilter(
        @RequestParam(name = "dataInicio", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
        @RequestParam(name = "dataFim", required = false)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
        @RequestParam(name = "valorMinimo", required = false)
        Float valorMinimo
    ) {
        return shopService.getShopByFilters(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
        @RequestParam(name = "dataInicio", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
        @RequestParam(name = "dataFim", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim
    ) {
        return shopService.getReportByDate(dataInicio, dataFim);
    }
}