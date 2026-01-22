package com.api_shopping.service;

import com.api_shopping.dto.ShopDTO;
import com.api_shopping.dto.ShopReportDTO;
import com.api_shopping.entities.Shop;
import com.api_shopping.repository.IShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private IShopRepository shopRepository;

    /**
     * Recupera Lista de Compras dos Usuários
     * @return List ShopDTO
     */
    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
            .stream()
            .map(ShopDTO::convertToShopDTO)
            . collect(Collectors.toList());
    }

    /**
     * Recupera uma Compra pela identificação do Usuário
     * @param userIdentifier
     * @return List ShopDTO
     */
    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
            .stream()
            .map(ShopDTO::convertToShopDTO)
            .collect(Collectors.toList());
    }

    /**
     * Recupera todas as compras por uma Data
     * @param shopDTO
     * @return List ShopDTO
     */
    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops
            .stream()
            .map(ShopDTO::convertToShopDTO)
            .collect(Collectors.toList());
    }

    /**
     * Recupera uma Compra especifica pelo ID
     * @param productId
     * @return ShopDTO
     */
    public ShopDTO findById(Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        if (shop.isPresent()) {
            return ShopDTO.convertToShopDTO(shop.get());
        }
        return null;
    }

    /**
     * Realiza o cálculo e Salva uma nova Compra
     * @param shopDTO
     * @return ShopDTO
     */
    public ShopDTO save(ShopDTO shopDTO) {
        shopDTO.setTotal(shopDTO.getItemDTOS()
            .stream()
            .map(x -> x.getPrice())
            .reduce((float) 0, Float::sum)
        );

        Shop shop = Shop.convertToShop(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return ShopDTO.convertToShopDTO(shop);
    }

    /**
     * Busca uma Compra por filtros personalizados (dataInicio, dataFim, valorMinimo)
     * @param dataInicio
     * @param dataFim
     * @param valorMinimo
     * @return ShopDTO
     */
    public List<ShopDTO> getShopByFiler(Date dataInicio, Date dataFim, Float valorMinimo) {
        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops
            .stream()
            .map(ShopDTO::convertToShopDTO)
            .collect(Collectors.toList());
    }

    /**
     * Gera Relatório por Data (dataInicio, dataFim)
     * @param dataInicio
     * @param dataFim
     * @return ShopReportDTO
     */
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
}