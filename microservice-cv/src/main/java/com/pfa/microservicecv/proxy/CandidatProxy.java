package com.pfa.microservicecv.proxy;

import com.pfa.microservicecv.beans.CandidatBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;


@FeignClient(name = "zuul-server", decode404 = true)
@RibbonClient(name = "microservice-users")

public interface CandidatProxy {

    @GetMapping("/microservice-users/candidats/findById/{id}")
    CandidatBean findCandidatById(@Valid @PathVariable Long id);
}