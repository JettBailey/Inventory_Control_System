package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 *
 *
 *
 *
 */
@Controller
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel){
        Part part=new OutsourcedPart();
        theModel.addAttribute("outsourcedpart",part);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, BindingResult theBindingResult,  Model theModel){
        theModel.addAttribute("outsourcedpart",part);
        if(bindingResult.hasErrors()){
            return "OutsourcedPartForm";
        }

        if (part.getInv() < part.getMinInv()){
            theBindingResult.rejectValue("inv", "error.inv.low", "Inventory cannot be less than the minimum of " + part.getMinInv());

            return "OutsourcedPartForm";
        }
        else if (part.getInv() > part.getMaxInv()){
            theBindingResult.rejectValue("inv", "error.inv.high", "Inventory cannot be greater than the maximum of " + part.getMaxInv());

            return "OutsourcedPartForm";
        }

        OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
        OutsourcedPart existingPart = repo.findById((int) part.getId());
        if (existingPart != null) {
            part.setProducts(existingPart.getProducts());
        }
        repo.save(part);

        return "confirmationaddpart";
    }



}
