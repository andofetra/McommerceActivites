package com.clientui.proxies;

import com.clientui.beans.ExpeditionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @PostMapping("/microservice-expedition/expedition")
    void addExpedition(@RequestBody @NotNull ExpeditionBean exp);

    @GetMapping("/microservice-expedition/expedition/{id}")
    ExpeditionBean getExpedition(@PathVariable Integer id);

    @PutMapping("/microservice-expedition/expedition")
    ResponseEntity<ExpeditionBean> updateExpedition(@RequestBody @NotNull ExpeditionBean exp);
}
