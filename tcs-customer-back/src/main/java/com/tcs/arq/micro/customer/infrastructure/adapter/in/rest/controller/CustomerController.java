package com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.controller;

import com.tcs.arq.micro.customer.domain.model.Customer;
import com.tcs.arq.micro.customer.domain.port.in.usecase.*;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.request.CustomerRequest;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.response.CustomerResponse;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.dto.response.DeleteCustomerResponse;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.mapper.request.CustomerRequestMapper;
import com.tcs.arq.micro.customer.infrastructure.adapter.in.rest.mapper.response.CustomerResponseMapper;
import com.tcs.arq.micro.customer.utils.CustomerEndpointsName;
import com.tcs.arq.micro.customer.utils.CustomerMessagesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerEndpointsName.ControllerApi.BASE_API)
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetAllCustomersPagedUseCase getAllCustomersPagedUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final LogicalDeleteCustomerUseCase logicalDeleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final GetCustomerByNameUseCase getCustomerByNameUseCase;

    @PostMapping(CustomerEndpointsName.ControllerApi.CREATE)
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        Customer customerModel = CustomerRequestMapper.INSTANCE.toModel(request);
        Customer created = createCustomerUseCase.createCustomer(customerModel);
        CustomerResponse customerResponse = CustomerResponseMapper.INSTANCE.toDTO(created);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping(CustomerEndpointsName.ControllerApi.SEARCH)
    public ResponseEntity<CustomerResponse> getCustomerByName(@RequestParam String name) {
        Customer customer = getCustomerByNameUseCase.getCustomerByName(name);
        CustomerResponse customerResponse = CustomerResponseMapper.INSTANCE.toDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping(CustomerEndpointsName.ControllerApi.ID_IN_PATH_CUSTOMER)
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long clientId) {
        Customer customerById = getCustomerByIdUseCase.findCustomerById(clientId);
        CustomerResponse customerResponse = CustomerResponseMapper.INSTANCE.toDTO(customerById);
        return ResponseEntity.ok(customerResponse);
    }

    @RequestMapping(CustomerEndpointsName.ControllerApi.ALL)
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(@RequestParam(required = false) Boolean status) {
        List<Customer> customerEntities = getAllCustomersUseCase.getAllCustomers(status);
        List<CustomerResponse> customerResponses = CustomerResponseMapper.INSTANCE.toDTOList(customerEntities);
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping(CustomerEndpointsName.ControllerApi.PAGINATE)
    public ResponseEntity<Page<CustomerResponse>> getAllDataCustomersPaged(
            @RequestParam(defaultValue = CustomerEndpointsName.ControllerApi.DEFAULT_PAGE_INIT) int page,
            @RequestParam(defaultValue = CustomerEndpointsName.ControllerApi.DEFAULT_PAGE_FINAL) int size,
            @RequestParam(defaultValue = CustomerEndpointsName.ControllerApi.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = CustomerEndpointsName.ControllerApi.DEFAULT_DIRECTION) String direction,
            @RequestParam(required = false) Boolean status
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Customer> customerPage = getAllCustomersPagedUseCase.getAllCustomersPaged(pageable, status);
        Page<CustomerResponse> responsePage = customerPage.map(CustomerResponseMapper.INSTANCE::toDTO);

        return ResponseEntity.ok(responsePage);
    }

    @PutMapping(CustomerEndpointsName.ControllerApi.ID_IN_PATH_CUSTOMER)
    public ResponseEntity<CustomerResponse> UPDATECustomer(@PathVariable Long clientId, @RequestBody @Valid CustomerRequest request) {
        Customer customerModel = CustomerRequestMapper.INSTANCE.toModel(request);
        customerModel.setClientId(clientId);

        Customer updatedCustomer = updateCustomerUseCase.updateCustomer(customerModel);
        CustomerResponse customerResponse = CustomerResponseMapper.INSTANCE.toDTO(updatedCustomer);
        return new ResponseEntity<>(customerResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(CustomerEndpointsName.ControllerApi.ID_IN_PATH_CUSTOMER)
    public ResponseEntity<DeleteCustomerResponse> deleteCustomer(@PathVariable Long clientId) {
        Customer deleteCustomer = logicalDeleteCustomerUseCase.logicalDeleteCustomer(clientId);
        CustomerResponse customerResponse = CustomerResponseMapper.INSTANCE.toDTO(deleteCustomer);
        DeleteCustomerResponse response = new DeleteCustomerResponse(
                CustomerMessagesUtil.Message.CUSTOMER_DELETED_SUCCESSFULLY, customerResponse
        );
        return ResponseEntity.ok(response);
    }
}
