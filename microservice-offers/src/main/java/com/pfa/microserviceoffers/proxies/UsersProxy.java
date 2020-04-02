package com.pfa.microserviceoffers.proxies;

import com.pfa.microserviceoffers.proxies.beans.UserBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@FeignClient(name = "microservice-users",url="localhost:9220", decode404 = true)
@RibbonClient(name = "microservice-users")
public interface UsersProxy {

    @GetMapping("/managers/findById/{id}")
    UserBean findManagerById(@Valid @PathVariable Long id);

    @GetMapping("/candidats/findById/{id}")
    UserBean findCandidatById(@Valid @PathVariable Long id);
}