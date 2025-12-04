package br.com.leandrosnazareth.pdvapi.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import br.com.leandrosnazareth.pdvapi.exception.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandrosnazareth.pdvapi.config.SpringFoxConfig;
import br.com.leandrosnazareth.pdvapi.domain.dto.SaleDTO;
import br.com.leandrosnazareth.pdvapi.exception.InsufficientStockException;
import br.com.leandrosnazareth.pdvapi.exception.InvalidItemException;
import br.com.leandrosnazareth.pdvapi.exception.ResourceNotFoundException;
import br.com.leandrosnazareth.pdvapi.service.SaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pdv/sale")
@Api(tags = { SpringFoxConfig.SALE_TAG })
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar venda pelo ID")
    public ResponseEntity<SaleDTO> findSaleById(@PathVariable Long id) throws ResourceNotFoundException {
        SaleDTO saleDto = saleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma venda com id: " + id));
        return ResponseEntity.ok().body(saleDto);
    }

    @PostMapping
    @ApiOperation(value = "Registra uma venda")
    public ResponseEntity<?> createSale(@Valid @RequestBody SaleDTO saleDto) {
        try {
            return ResponseEntity.ok().body(saleService.save(saleDto));
        } catch (InvalidItemException | InsufficientStockException | InvalidUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Listar todas vendas ativas com paginação")
    @GetMapping
    public Page<SaleDTO> findAllPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "5") Integer tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return saleService.findAll(pageRequest);
    }

    @ApiOperation(value = "Deletar venda pelo ID")
    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id)
            throws ResourceNotFoundException {
        SaleDTO saleDto = saleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada uma venda com id: " + id));
        saleService.delete(saleDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar venda")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable(value = "id") Long id,
            @Valid @RequestBody SaleDTO saleDto) throws ResourceNotFoundException, InvalidItemException, InsufficientStockException, InvalidUserException {
        saleService.findById(saleDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi encontrada uma venda com id: " + saleDto.getId()));
        return ResponseEntity.ok(saleService.save(saleDto));
    }

    @GetMapping("total/{createdAt}")
    @ApiOperation(value = "Listar valor total de vendas no mes")
    public BigDecimal findValorTotalMonthAndYear2(@Valid @RequestBody LocalDateTime createdAt) {
        return saleService.findValorTotalMonthAndYear2(createdAt);
    }

    @GetMapping("somatotal")
    @ApiOperation(value = "Listar valor total de vendas")
    public BigDecimal findValorTotalSales() {
        return saleService.findValorTotalSales();
    }

    @ApiOperation(value = "Retornar quantidade de vendas")
    @GetMapping("count")
    public long countSales() {
        return saleService.countSales();
    }

    @ApiOperation(value = "Retornar ultimas 10 vendas")
    @GetMapping("limit")
    public List<SaleDTO> findTopByOrderByIdDesc() {
        return saleService.findTopByOrderByIdDesc();
    }
}
