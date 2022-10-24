package me.elmajni.billingservicevf.mappers;

import me.elmajni.billingservicevf.dtos.InvoiceResponseDTO;
import me.elmajni.billingservicevf.dtos.InvoiceRequestDTO;
import me.elmajni.billingservicevf.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
