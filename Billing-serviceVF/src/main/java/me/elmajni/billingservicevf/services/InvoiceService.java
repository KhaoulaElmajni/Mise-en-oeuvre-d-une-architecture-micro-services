package me.elmajni.billingservicevf.services;

import me.elmajni.billingservicevf.dtos.InvoiceResponseDTO;
import me.elmajni.billingservicevf.dtos.InvoiceRequestDTO;
import me.elmajni.billingservicevf.exception.CustomerNotFoundException;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException;
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> getInvoices();
    List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);

}
