package br.com.leandrosnazareth.pdvapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.leandrosnazareth.pdvapi.domain.dto.ProductSoldDTO;
import br.com.leandrosnazareth.pdvapi.exception.InsufficientStockException;
import br.com.leandrosnazareth.pdvapi.exception.InvalidItemException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

import br.com.leandrosnazareth.pdvapi.domain.dto.SaleDTO;
import br.com.leandrosnazareth.pdvapi.domain.entity.Sale;
import br.com.leandrosnazareth.pdvapi.domain.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductService productService;

    public SaleDTO save(SaleDTO saleDto) throws InsufficientStockException, InvalidItemException {
        productService.processSaleProducts(saleDto.getSoldProducts());

        Sale sale = saleRepository.save(modelMapper.map(saleDto, Sale.class));
        return modelMapper.map(sale, SaleDTO.class);
    }

    public Page<SaleDTO> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable)
                .map((sale -> DozerBeanMapperBuilder.buildDefault()// converte pag<Sale> para page<saledto>
                        .map(sale, SaleDTO.class)));
    }

    public Optional<SaleDTO> findById(long id) {
        return saleRepository.findById(id)
                .map(sale -> modelMapper.map(sale, SaleDTO.class));
    }

    public void delete(SaleDTO saletDto) {
        Sale sale = modelMapper.map(saletDto, Sale.class);
        saleRepository.delete(sale);
    }

    public BigDecimal findValorTotalMonthAndYear2(@Valid LocalDateTime createdAt) {
        return saleRepository.findValorTotalMonthAndYear2(createdAt);
    }

    public BigDecimal findValorTotalSales() {
        return saleRepository.findValorTotalSales();
    }

    public long countSales() {
        return saleRepository.count();
    }

    public List<SaleDTO> findTopByOrderByIdDesc() {
        return saleRepository.findTop5ByOrderByIdDesc().stream()
                .map(sale -> modelMapper.map(sale, SaleDTO.class))
                .collect(Collectors.toList());
    }
}
