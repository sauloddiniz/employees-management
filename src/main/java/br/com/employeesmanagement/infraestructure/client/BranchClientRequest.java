package br.com.employeesmanagement.infraestructure.client;

import br.com.employeesmanagement.infraestructure.client.config.BranchClientConfig;
import br.com.employeesmanagement.infraestructure.dto.BranchResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "branchClient", url = "http://localhost:8081", configuration = BranchClientConfig.class)
public interface BranchClientRequest {

    @GetMapping(value = "/branch/{branchId}")
    ResponseEntity<BranchResponseDto> getBranch(@PathVariable Long branchId);
}
