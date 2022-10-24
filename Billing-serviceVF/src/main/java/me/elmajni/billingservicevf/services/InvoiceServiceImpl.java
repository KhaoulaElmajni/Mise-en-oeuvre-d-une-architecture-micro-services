package me.elmajni.billingservicevf.services;

import me.elmajni.billingservicevf.dtos.InvoiceResponseDTO;
import me.elmajni.billingservicevf.dtos.InvoiceRequestDTO;
import me.elmajni.billingservicevf.entities.Invoice;
import me.elmajni.billingservicevf.exception.CustomerNotFoundException;
import me.elmajni.billingservicevf.feign.CustomerRestClient;
import me.elmajni.billingservicevf.mappers.InvoiceMapper;
import me.elmajni.billingservicevf.models.Customer;
import me.elmajni.billingservicevf.repositories.InvoiceRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRespository invoiceRespository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRespository invoiceRespository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRespository = invoiceRespository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException {
        /***
         * vérification de l'intégrité référentiel Invoice / customer
         */
        Customer customer=null;
        try {
            customer = customerRestClient.getCustomerById(invoiceRequestDTO.getCustomerId());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }

        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());

        Invoice savedInvoice = invoiceRespository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRespository.findById(invoiceId).get();
        Customer customer = customerRestClient.getCustomerById(invoice.getCustomerId());
        invoice.setCustomer(customer);
        InvoiceResponseDTO invoicResponseDTO = invoiceMapper.fromInvoice(invoice);
        return invoicResponseDTO;
    }

    @Override
    public List<InvoiceResponseDTO> getInvoices() {
        List<Invoice> invoices = invoiceRespository.findAll();
        for (Invoice invoice :invoices) {
            Customer customer = customerRestClient.getCustomerById(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        List<InvoiceResponseDTO> invoiceResponseDTOS = invoices
                .stream()
                .map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
        return invoiceResponseDTOS;
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices = invoiceRespository.findByCustomerId(customerId);
        for (Invoice invoice :invoices) {
            Customer customer = customerRestClient.getCustomerById(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        List<InvoiceResponseDTO> invoicesResponseDTO = invoices
                .stream()
                .map(invoice -> invoiceMapper.fromInvoice(invoice))
                .collect(Collectors.toList());
        return invoicesResponseDTO;
    }
}
