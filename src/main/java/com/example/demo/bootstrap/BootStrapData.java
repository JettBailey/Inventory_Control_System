package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        OutsourcedPart DoorKnob= new OutsourcedPart();
        DoorKnob.setCompanyName("Hardware Is Us");
        DoorKnob.setName("Door Knob");
        DoorKnob.setInv(25);
        DoorKnob.setPrice(10.0);
        DoorKnob.setId(1000L);
        outsourcedPartRepository.save(DoorKnob);

        OutsourcedPart hinge= new OutsourcedPart();
        hinge.setCompanyName("Hardware Is Us");
        hinge.setName("Hinge");
        hinge.setInv(55);
        hinge.setPrice(5.0);
        hinge.setId(1001L);
        outsourcedPartRepository.save(hinge);

        OutsourcedPart dowel = new OutsourcedPart();
        dowel.setCompanyName("Wood Suppliers");
        dowel.setName("Dowel");
        dowel.setInv(450);
        dowel.setPrice(0.10);
        dowel.setId(1002L);
        outsourcedPartRepository.save(dowel);

        OutsourcedPart handle = new OutsourcedPart();
        handle.setCompanyName("Hardware Is Us");
        handle.setName("Handle");
        handle.setInv(20);
        handle.setPrice(3.0);
        handle.setId(1003L);
        outsourcedPartRepository.save(handle);

        OutsourcedPart plank = new OutsourcedPart();
        plank.setCompanyName("Wood Suppliers");
        plank.setName("Plank");
        plank.setInv(74);
        plank.setPrice(17.0);
        plank.setId(1004L);
        outsourcedPartRepository.save(plank);

        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if (part.getName().equals("Door Knob") ||
                    part.getName().equals("Hinge") ||
                    part.getName().equals("Dowel") ||
                    part.getName().equals("Handle") ||
                    part.getName().equals("Plank")) {
                thePart = part;
            }
        }

        System.out.println(thePart.getCompanyName());

        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }


        Product dresser= new Product("dresser",200.0,15);
        Product desk= new Product("desk",100.0,10);
        Product table= new Product("table",100.0,10);
        Product shelf= new Product("shelf",100.0,10);
        Product door= new Product("door",100.0,10);
        productRepository.save(dresser);
        productRepository.save(desk);
        productRepository.save(table);
        productRepository.save(shelf);
        productRepository.save(door);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
